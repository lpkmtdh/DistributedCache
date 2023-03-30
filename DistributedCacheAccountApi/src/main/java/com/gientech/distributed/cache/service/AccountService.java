package com.gientech.distributed.cache.service;

import com.gientech.distributed.cache.domain.User;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.domain.Account;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public interface AccountService {
    @GetMapping
    Result<Account> getAccountById(@RequestParam("userId")Integer userId);
    @GetMapping("/all")
    Result<List<Account>> getAccountList();
    @PutMapping
    Result<Integer> updateAccount(@RequestBody Account account);
    @PostMapping
    Result<Integer> insertAccount(@RequestBody Account account);
    @DeleteMapping
    Result<Integer> deleteAccount(@RequestParam("userId")Integer userId);

    @PostMapping("/account/user")
    Result<Integer> insertUser(@RequestBody User user);
}
