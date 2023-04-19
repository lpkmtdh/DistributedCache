package com.gientech.loadbalance;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.reactive.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.reactive.EmptyResponse;
import org.springframework.cloud.client.loadbalancer.reactive.Request;
import org.springframework.cloud.client.loadbalancer.reactive.Response;
import org.springframework.cloud.loadbalancer.core.*;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class GrayLoadBalancer implements ReactorServiceInstanceLoadBalancer {
    private static final Log log = LogFactory.getLog(GrayLoadBalancer.class);
    private ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider;
    private  String serviceId;

    public GrayLoadBalancer(ObjectProvider<ServiceInstanceListSupplier> serviceInstanceListSupplierProvider, String serviceId) {
        this.serviceId = serviceId;
        this.serviceInstanceListSupplierProvider = serviceInstanceListSupplierProvider;
    }


    @Override
    public Mono<Response<ServiceInstance>> choose(Request request) {
        HttpHeaders headers = (HttpHeaders) request.getContext();
        if (this.serviceInstanceListSupplierProvider != null) {
            ServiceInstanceListSupplier supplier = (ServiceInstanceListSupplier)this.serviceInstanceListSupplierProvider.getIfAvailable(NoopServiceInstanceListSupplier::new);
            return ((Flux)supplier.get()).next().map(list->getInstanceResponse((List<ServiceInstance>)list,headers));
        }

        return null;


    }

    private Response<ServiceInstance> getInstanceResponse(List<ServiceInstance> instances,HttpHeaders headers) {
        if (instances.isEmpty()) {
            return getServiceInstanceEmptyResponse();
        } else {
            return getServiceInstanceResponseByVersion(instances, headers);
        }
    }

    /**
     * 根据版本进行分发
     * @param instances
     * @param headers
     * @return
     */
    private Response<ServiceInstance> getServiceInstanceResponseByVersion(List<ServiceInstance> instances, HttpHeaders headers) {
        String versionNo = headers.getFirst("version");
        ServiceInstance serviceInstance = null;

        if(StringUtils.isEmpty(versionNo)){
            int pos = new Random().nextInt(10);
            serviceInstance = instances.get(pos % instances.size());
            return new DefaultResponse(serviceInstance);
        }

        Map<String,String> versionMap = new HashMap<>();
        versionMap.put("version",versionNo);
        final Set<Map.Entry<String,String>> attributes =
                Collections.unmodifiableSet(versionMap.entrySet());

        for (ServiceInstance instance : instances) {
            Map<String,String> metadata = instance.getMetadata();
            if(metadata.entrySet().containsAll(attributes)){
                serviceInstance = instance;
                break;
            }
        }

        if(ObjectUtils.isEmpty(serviceInstance)){
            return getServiceInstanceEmptyResponse();
        }
        return new DefaultResponse(serviceInstance);
    }

    private Response<ServiceInstance> getServiceInstanceEmptyResponse() {
        log.warn("No servers available for service: " + this.serviceId);
        return new EmptyResponse();
    }
}