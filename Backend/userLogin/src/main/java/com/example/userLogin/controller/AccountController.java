package com.example.userLogin.controller;

import com.example.userLogin.module.Account;
import com.example.userLogin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.userLogin.service.AccountService;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AccountController {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    AccountService accountService;

    @GetMapping("/account/all")
    List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    @GetMapping("/account/{id}")
    Account getAccountById(@PathVariable String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found for id: " + id));
    }

    @PostMapping("/account")
    Account createAccount(@RequestBody Account newAccount) {
        return accountRepository.save(newAccount);
    }

    @PutMapping("/account/{id}")
    Account updateAccount(@PathVariable String id, @RequestBody Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found for id: " + id));
        existingAccount.setUsername(updatedAccount.getUsername());
        existingAccount.setPassword(updatedAccount.getPassword());
        return accountRepository.save(existingAccount);
    }

    @DeleteMapping("/account/{id}")
    String deleteAccount(@PathVariable String id) {
        accountRepository.deleteById(id);
        return "Deleted Account with id:" + id;
    }

    @GetMapping("/users/login")
    Boolean userLogin(@RequestParam String username, @RequestParam String password) {
        boolean isValid = accountService.isUserValid(username, password);
        return isValid;
    }
}

