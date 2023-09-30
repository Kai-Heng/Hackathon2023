package com.example.demo2.service;

import com.example.demo2.module.UsersAccount;
import com.example.demo2.repository.UsersAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersAccountService {

    @Autowired
    UsersAccountRepository accountRepo;

    public UsersAccount getAccountDetailsById(Long id) {
        Optional<UsersAccount> accountOptional = accountRepo.findById(id);

        if (accountOptional.isPresent()) {
            return accountOptional.get(); // Extract the Account from Optional
        } else {
            // Handle the case when the account is not found, e.g., by throwing an exception or returning null.
            throw new RuntimeException("Account not found for id: " + id);
        }
    }
}
