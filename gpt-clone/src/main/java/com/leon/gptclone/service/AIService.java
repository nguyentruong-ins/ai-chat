package com.leon.gptclone.service;

import com.leon.gptclone.configuration.AIConfig;
import com.leon.gptclone.controller.AIController;
import com.leon.gptclone.model.ChatLine;
import com.leon.gptclone.model.CurrentUser;
import com.leon.gptclone.model.Question;
import com.leon.gptclone.model.response.AIResponse;
import com.leon.gptclone.model.response.ChatListResponse;
import com.leon.gptclone.model.response.Response;
import com.leon.gptclone.repository.ChatLineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.ResourceBundle;

@Service
public class AIService {

    @Autowired
    AIConfig aiConfig;

    @Autowired
    ChatLineRepository chatLineRepository;

    public ResponseEntity<Object> genAns(Question question){

        if (CurrentUser.user == null)
            return new ResponseEntity<>(new Response("0005", "You have not login yet"), HttpStatus.BAD_REQUEST);

        if (question.getQuestion().length() > 500){
            return new ResponseEntity<>(new Response("0007", "Your question is too long, the question's length should <= 500 charaters"), HttpStatus.BAD_REQUEST);
        }

        RestTemplate restTemplate = aiConfig.template();
        try {
            String response = restTemplate.getForObject("http://localhost:5000/helloworld/" + question.getQuestion(), String.class);
            response = response.replace("\"", "");
            chatLineRepository.save(new ChatLine(CurrentUser.user, question.getQuestion(), response));
            return new ResponseEntity<>(new AIResponse(response, "0000", "Success"), HttpStatus.OK);
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new RuntimeException(e);
            return new ResponseEntity<>(new Response("0006", "The AI Server is not response"), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public ResponseEntity<Object> getChats(String username){
        List<ChatLine> chatLines = chatLineRepository.findChatLineByUser_Username(username);
        return new ResponseEntity<>(new ChatListResponse(chatLines, "0000", "success"), HttpStatus.OK);
    }

    public ResponseEntity<Object> delChats(String username){
        chatLineRepository.deleteChatLinesByUser_Username(username);
        return new ResponseEntity<>(new Response("0000", "success"), HttpStatus.OK);
    }
}
