package com.gientech.distributed.cache.controller;

import com.gientech.distributed.cache.domain.Account;
import com.gientech.distributed.cache.domain.User;
import com.gientech.distributed.cache.pojo.Result;
import com.gientech.distributed.cache.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName AccountController
 * @Description
 * @Author Kevin.Lian
 * @Date 2023-03-02 16:02
 * @Version 1.0
 */
@RestController
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping
    public Result<Account> getAccountById(@RequestParam("userId") Integer userId){
        return accountService.getAccountById(userId);
    }

    @GetMapping("/all")
    public Result<List<Account>> getAccountList(){
        return accountService.getAccountList();
    }

    @PostMapping
    public Result<Integer> insertAccount(@RequestBody Account account){
        return accountService.insertAccount(account);
    }

    @PutMapping
    public Result<Integer> updateAccount(@RequestBody Account account){
        return accountService.updateAccount(account);
    }

    @DeleteMapping
    public Result<Integer> deleteAccount(@RequestParam("userId") Integer userId){
        return accountService.deleteAccount(userId);
    }


    @PostMapping("/user")
    public Result<Integer> insertUser(@RequestBody User user){
        return accountService.insertUser(user);
    }

}