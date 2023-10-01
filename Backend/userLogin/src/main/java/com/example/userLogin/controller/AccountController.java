package com.example.userLogin.controller;

import com.example.userLogin.module.Account;
import com.example.userLogin.repository.AccountRepository;
import com.example.userLogin.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account") // Updated base URL
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    // ... Other methods ...

    @PostMapping("/post")
    Account postAccount(@RequestBody Account newAccount) {
        accountRepository.save(newAccount);
        return newAccount;
    }

    @GetMapping("/{id}")
    Account getAccount(@PathVariable String id) {
        return accountService.getAccountDetailsById(id);
    }

    @PutMapping("/updateUsername/{id}")
    Account updateUsername(@RequestBody Account updatedAccount, @PathVariable String id) {
        Account oldUser = accountService.getAccountDetailsById(id);
        oldUser.setUsername(updatedAccount.getUsername());
        accountRepository.save(oldUser);
        return oldUser;
    }

    @PutMapping("/updatePassword/{id}")
    Account updatePassword(@RequestBody Account updatedPassword, @PathVariable String id) {
        Account oldPassword = accountService.getAccountDetailsById(id);
        oldPassword.setPassword(updatedPassword.getPassword());
        accountRepository.save(oldPassword);
        return oldPassword;
    }

    @DeleteMapping("/deleteAccount/{id}")
    String deleteAccount(@PathVariable String id) {
        accountRepository.deleteById(id);
        return "Deleted Account with id:" + id;
    }
}
