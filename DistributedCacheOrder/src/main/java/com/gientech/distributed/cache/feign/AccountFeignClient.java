package com.gientech.distributed.cache.feign;

import com.gientech.distributed.cache.service.AccountService;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ClassName AccountFeignClient
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 17:11
 * @Version 1.0
 */
@FeignClient(name = "account",url = "http://localhost:9001/account")
public interface AccountFeignClient extends AccountService {
}