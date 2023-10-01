package com.example.userLogin.service;

import com.example.userLogin.module.Account;
import com.example.userLogin.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    AccountRepository accountRepository;

    public Account getAccountDetailsById(String id) {
        Optional<Account> accountOptional = accountRepository.findById(id);

        if (accountOptional.isPresent()) {
            return accountOptional.get();
        } else {
            throw new RuntimeException("Account not found for id: " + id);
        }
    }

    public boolean isUserValid(String username, String password) {
        Account user = accountRepository.findByUsername(username);

        return user != null && user.getPassword().equals(password);
    }
}
