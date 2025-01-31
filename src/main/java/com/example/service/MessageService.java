package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public ResponseEntity<Integer> deleteMessageById(Integer messageId) {
      
        if (messageRepository.existsById(messageId)) {
            messageRepository.deleteById(messageId);
            return new ResponseEntity<>(1, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }


    public Message createMessage(Message message) throws IllegalArgumentException {
        if (message.getMessageText() == null || message.getMessageText().trim().isEmpty()) {
            throw new IllegalArgumentException("Message text cannot be blank.");
        }
        if (message.getMessageText().length() > 255) {
            throw new IllegalArgumentException("Message text cannot exceed 255 characters.");
        }
        if (!accountRepository.existsById(message.getPostedBy())) {
            throw new IllegalArgumentException("Invalid user ID.");
        }

        return messageRepository.save(message);
    }



    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }



    public Optional<Message> getMessageById(Integer messageId) {
        return messageRepository.findById(messageId);
    }



    public int updateMessageText(Integer messageId, String newMessageText) throws IllegalArgumentException {
        if (newMessageText == null || newMessageText.trim().isEmpty()) {
            throw new IllegalArgumentException("Message text cannot be blank.");
        }
        if (newMessageText.length() > 255) {
            throw new IllegalArgumentException("Message text cannot exceed 255 characters.");
        }

        Optional<Message> existingMessage = messageRepository.findById(messageId);
        if (existingMessage.isPresent()) {
            Message message = existingMessage.get();
            message.setMessageText(newMessageText);
            messageRepository.save(message);
            return 1; 
        } else {
            throw new IllegalArgumentException("Message with given ID does not exist.");
        }
    }




    public List<Message> findAllMessagesByUserId(Integer userid){
        return messageRepository.findAllByPostedBy(userid);
    }
}
