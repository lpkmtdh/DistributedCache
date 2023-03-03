package com.gientech.distributed.cache.cache;

import com.gientech.distributed.cache.domain.Account;

import java.util.List;

public interface Cache {
    Account getAccountByUserId(Integer userId);
    Integer deleteByUserId(Integer userId);
    Integer insertByUserId(Integer userId);
    List<Account> getAllAccount();
}
