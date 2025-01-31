package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    public ResponseEntity<Account> registerUser(Account account) {
     
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        Account savedAccount = accountRepository.save(account);
        return new ResponseEntity<>(savedAccount, HttpStatus.OK);
    }

   

    public Optional<Account> validateLogin(String username, String password) {
        return accountRepository.findByUsername(username)
            .filter(account -> account.getPassword().equals(password));
    }
}
