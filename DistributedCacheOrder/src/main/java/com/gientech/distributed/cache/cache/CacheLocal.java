package com.gientech.distributed.cache.cache;

import com.gientech.distributed.cache.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName CacheLocal
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:55
 * @Version 1.0
 */
@Component("cacheLocal")
public class CacheLocal implements Cache{
    private ConcurrentHashMap<Integer, Account> accounts = new ConcurrentHashMap<>();
    @Autowired
    @Qualifier("cacheRedis")
    private Cache cacheRedis;

    @Override
    public Account getAccountByUserId(Integer userId) {
        Account account = accounts.get(userId);
        if(account != null){
            return account;
        }
        synchronized(this){
            account = accounts.get(userId);
            if(account != null){
                return account;
            }
            account = cacheRedis.getAccountByUserId(userId);
            if(account != null){
                accounts.put(userId,account);
                return account;
            }
        }
        return null;
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return accounts.remove(userId) == null ?0:1;
    }

    @Override
    public Integer insertByUserId(Integer userId) {
        return this.getAccountByUserId(userId) == null?0:1;
    }

    @Override
    public List<Account> getAllAccount() {
        List<Account> all = new ArrayList<>();
        for(Account account : accounts.values()){
            all.add(account);
        }
        return all;
    }
}