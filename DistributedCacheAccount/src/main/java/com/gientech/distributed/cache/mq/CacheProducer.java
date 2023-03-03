package com.gientech.distributed.cache.mq;

import com.gientech.distributed.cache.pojo.MqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @ClassName CacheProducer
 * @Description
 * @Author Kevin.Lian
 * @Date 2022-12-29 10:04
 * @Version 1.0
 */

@Component
@Slf4j
public class CacheProducer {
    @Resource
    private RocketMQTemplate rocketMQTemplate;

    public SendResult sendMessage(String topic, MqMessage message){
       return this.rocketMQTemplate.syncSend(topic,message);
    }
}