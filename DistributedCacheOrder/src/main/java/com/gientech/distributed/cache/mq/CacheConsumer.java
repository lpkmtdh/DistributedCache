package com.gientech.distributed.cache.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gientech.distributed.cache.cache.Cache;
import com.gientech.distributed.cache.pojo.MqMessage;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.ConsumeMode;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * @ClassName Consumer
 * @Description
 * @Author Kevin.Lian
 * @Date 2022-12-29 10:04
 * @Version 1.0
 */

@Component
@Slf4j
@ConditionalOnProperty(
        prefix = "rocketmq",
        value = {"name-server"}
)
@RocketMQMessageListener(consumerGroup = "${rocketmq.consumer.group}", topic = "${rocketmq.topic}",consumeMode= ConsumeMode.CONCURRENTLY)
public class CacheConsumer implements RocketMQListener<MqMessage> {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    @Qualifier("cacheLocal")
    private Cache cacheLocal;

    @Override
    public void onMessage(MqMessage message) {
        try {
            log.info("message: {}",mapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        switch (message.getType()){
            case "insert" : insert(message.getUserId());break;
            case "update" : update(message.getUserId());break;
            case "delete" : delete(message.getUserId());break;
            default:break;
        }

    }

    private void update(Integer userId) {
        cacheLocal.deleteByUserId(userId);
        cacheLocal.insertByUserId(userId);
        log.info("Cache update success: {}",cacheLocal.getAllAccount());
    }

    private void insert(Integer userId) {
        cacheLocal.deleteByUserId(userId);
        cacheLocal.insertByUserId(userId);
        log.info("Cache insert success: {}",cacheLocal.getAllAccount());
    }

    private void delete(Integer userId){
        cacheLocal.deleteByUserId(userId);
        log.info("Cache delete success: {}",cacheLocal.getAllAccount());
    }
}