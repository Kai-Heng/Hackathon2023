package com.example.demo2.controller;

import com.example.demo2.module.UsersAccount;
import com.example.demo2.repository.UsersAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsersAccountController {

    @Autowired
    UsersAccountRepository accountRepository;

    //This gets the list of all the accounts registered
    @GetMapping("account/all")
    List<UsersAccount> GetAllAccounts(){
            return accountRepository.findAll();
    }

    //This get the information of the user by ID from the MySQL tables
    @GetMapping("getAccountById/{id}")
    UsersAccount fetchDetailsById(@PathVariable Long id){
        return accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Account not found for id: " + id));
    }

    //This post the account in the url on postman
    @PostMapping("account/post/{u}/{p}/{ut}")
    UsersAccount PostAccountByPath(@PathVariable String u, @PathVariable String p, @PathVariable String e){
        UsersAccount newAccount = new UsersAccount();
        newAccount.setUsername(u);
        newAccount.setPassword(p);
        newAccount.setEmail(e);
        accountRepository.save(newAccount);
        return newAccount;
    }

    //This post an account with JSON on postman
    @PostMapping("account/post")
    UsersAccount PostAccountByPath(@RequestBody UsersAccount newAccount){
        accountRepository.save(newAccount);
        return newAccount;
    }

    @GetMapping("/account/{id}")
    UsersAccount getUser(@PathVariable Long id) {
        return accountRepository.findById(id).
                orElseThrow(RuntimeException::new);
    }

//NEED TO WORK ON THIS :

    // Updating the account user
    @PutMapping("/updateUsernameById/{id}")
    UsersAccount updateUsername(@RequestBody UsersAccount updatedAccount, @PathVariable Long id) {
        UsersAccount oldUser = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found for id: " + id));
        oldUser.setUsername(updatedAccount.getUsername());
        accountRepository.save(oldUser);
        return oldUser;
    }

    // Updating the account password
    @PutMapping("/updatePasswordById/{id}")
    UsersAccount updatePassword(@RequestBody UsersAccount updatedPassword, @PathVariable Long id) {
        UsersAccount oldPassword = accountRepository.findById(id).orElseThrow(() -> new RuntimeException("Account not found for id: " + id));
        oldPassword.setPassword(updatedPassword.getPassword());
        accountRepository.save(oldPassword);
        return oldPassword;
    }

    @DeleteMapping("/deleteAccountById/{id}")
    String deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return "Deleted Account with id:" + id;
    }
}
