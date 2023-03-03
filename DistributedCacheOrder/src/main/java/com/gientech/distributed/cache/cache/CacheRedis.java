package com.gientech.distributed.cache.cache;

import com.gientech.distributed.cache.domain.Account;
import com.gientech.distributed.cache.feign.AccountFeignClient;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.pojo.ResultCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName CacheRedis
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:55
 * @Version 1.0
 */
@Component("cacheRedis")
public class CacheRedis  implements Cache{
    @Autowired
    private AccountFeignClient accountFeignClient;
    @Override
    public Account getAccountByUserId(Integer userId) {
        Result<Account> account = accountFeignClient.getAccountById(userId);
        if(account == null || account.getCode() != ResultCode.SUCCESS.getCode()){
            return null;
        }
        return account.getData();
    }

    @Override
    public Integer deleteByUserId(Integer userId) {
        return 1;
    }

    @Override
    public Integer insertByUserId(Integer userId) {
        return 1;
    }

    @Override
    public List<Account> getAllAccount() {
        Result<List<Account>> accounts = accountFeignClient.getAccountList();
        if(accounts == null || accounts.getCode() != ResultCode.SUCCESS.getCode()){
            return null;
        }
        return accounts.getData();
    }
}