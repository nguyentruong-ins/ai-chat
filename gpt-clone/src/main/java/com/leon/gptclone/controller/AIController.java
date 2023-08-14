package com.leon.gptclone.controller;

import com.leon.gptclone.model.Question;
import com.leon.gptclone.model.response.AIResponse;
import com.leon.gptclone.service.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1")
public class AIController {
    @Autowired
    AIService aiService;

    @CrossOrigin("*")
    @PostMapping("/ask")
    public ResponseEntity<Object> genAns(@RequestBody Question question){
        return aiService.genAns(question);
    }

    @CrossOrigin("*")
    @DeleteMapping ("/chats/{username}")
    public ResponseEntity<Object> delChats(@PathVariable("username") String username){
        return aiService.delChats(username);
    }

    @CrossOrigin("*")
    @GetMapping("/chats/{username}")
    public ResponseEntity<Object> getChats(@PathVariable("username") String username){
        return aiService.getChats(username);
    }
}
