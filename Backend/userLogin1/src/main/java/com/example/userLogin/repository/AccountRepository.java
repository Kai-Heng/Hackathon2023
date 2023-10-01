package com.example.userLogin.repository;


import com.example.userLogin.module.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {



}
