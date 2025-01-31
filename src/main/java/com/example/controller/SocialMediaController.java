package com.example.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

@RestController
@RequestMapping
public class SocialMediaController {

    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        return accountService.registerUser(account);
    }

    

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Optional<Account> validAccount = accountService.validateLogin(account.getUsername(), account.getPassword());
        
        if (validAccount.isPresent()) {
            return new ResponseEntity<>(validAccount.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }





    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<String> deleteMessage(@PathVariable Integer messageId) {
        ResponseEntity<Integer> response = messageService.deleteMessageById(messageId);
        return response.getStatusCode() == HttpStatus.OK && response.getBody() == 0
            ? ResponseEntity.ok("")
            : ResponseEntity.ok(String.valueOf(response.getBody()));
    }


    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        try {
            Message createdMessage = messageService.createMessage(message);
            return new ResponseEntity<>(createdMessage, HttpStatus.OK);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }


    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable Integer messageId) {
        Optional<Message> message = messageService.getMessageById(messageId);
        return message.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.ok().body(null));
    }




    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Map<String, String> requestBody) {
        try {
            String newMessageText = requestBody.get("messageText");
            int rowsUpdated = messageService.updateMessageText(messageId, newMessageText);
            return ResponseEntity.ok(rowsUpdated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid request: " + e.getMessage());
        }
    }



    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> findAllMessagesByUserId(@PathVariable Integer accountId) {
        List<Message> messages = messageService.findAllMessagesByUserId(accountId);
        return ResponseEntity.ok(messages);
    }
}
