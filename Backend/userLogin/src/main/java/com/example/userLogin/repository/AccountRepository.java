package com.example.userLogin.repository;

import com.example.userLogin.module.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {
    Account findByUsername(String username);
}
