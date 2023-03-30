package com.gientech.distributed.cache.service.impl;

import com.gientech.distributed.cache.dao.UserMapper;
import com.gientech.distributed.cache.domain.Account;
import com.gientech.distributed.cache.domain.User;
import com.gientech.distributed.cache.mq.CacheProducer;
import com.gientech.distributed.cache.pojo.MqMessage;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.service.AccountService;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AccountServiceImpl
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 15:49
 * @Version 1.0
 */
@Service
public class AccountServiceImpl implements AccountService {

    private ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();

    @Autowired
    private UserMapper userMapper;
        
    @Autowired
    private CacheProducer cacheProducer;

    @Value("${rocketmq.topic}")
    private String topic;
    
    @PostConstruct
    public void init() {
        for (int i = 1; i <= 10; i++) {
            accounts.put(i, new Account(i, "User" + i));
        }
    }

    @Override
    public Result<Account> getAccountById(Integer userId) {
        if (userId == null) {
            Result.validateFailed();
        }
        return Result.success(accounts.get(userId));
    }

    @Override
    public Result<List<Account>> getAccountList() {
        List<Account> list = new ArrayList<>();
        for (Account account : accounts.values()) {
            list.add(account);
        }
        return Result.success(list);
    }

    @Override
    public Result<Integer> updateAccount(Account account) {
        if (account == null || account.getUserId() == null) {
            return Result.validateFailed();
        }
        accounts.put(account.getUserId(), account);
        mq("update",account.getUserId());
        return Result.success(1);
    }

    @Override
    public Result<Integer> insertAccount(Account account) {
        if (account == null || account.getUserId() == null) {
            return Result.validateFailed();
        }
        accounts.put(account.getUserId(), account);
        mq("insert",account.getUserId());
        return Result.success(1);
    }

    @Override
    public Result<Integer> deleteAccount(Integer userId) {
        if (userId == null) {
            return Result.validateFailed();
        }
        mq("delete",userId);
        return accounts.remove(userId) != null ? Result.success(1) : Result.failed();
    }

    @Override
    public Result<Integer> insertUser(User user) {
        if(user == null || user.getId() == null || user.getId() %2 ==0){
            throw new RuntimeException("尴尬了。。。");
        }
        int count = userMapper.insert(user);
        return Result.success(count);
    }

    private void mq(String type, Integer userId) {
        MqMessage message = new MqMessage();
        message.setType(type);
        message.setUserId(userId);
        SendResult sendResult = this.cacheProducer.sendMessage(topic, message);
        if(sendResult == null || !SendStatus.SEND_OK.equals(sendResult.getSendStatus())){
            throw new RuntimeException("send mq message error");
        }
    }
}