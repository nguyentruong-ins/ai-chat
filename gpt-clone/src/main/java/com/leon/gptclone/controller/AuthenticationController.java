package com.leon.gptclone.controller;

import com.leon.gptclone.model.response.LoginResponseDTO;
import com.leon.gptclone.model.User;
import com.leon.gptclone.service.AuthenticationService;
import jakarta.persistence.Column;
import jakarta.persistence.ColumnResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

//import java.util.Objects;

@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @CrossOrigin(value = "*", exposedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/regis")
    public ResponseEntity<Object> registerUser(@RequestBody User regisUser){
        return authenticationService.register(regisUser.getUsername(), regisUser.getPassword());
    }

    @CrossOrigin(value = "*", exposedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User loginUser){
        return authenticationService.login(loginUser.getUsername(), loginUser.getPassword());
    }

    @CrossOrigin(value = "*", exposedHeaders = "Access-Control-Allow-Origin")
    @PostMapping("/logout")
    public ResponseEntity<Object> logout(){
        return authenticationService.logout();
    }

    @CrossOrigin(value = "*", exposedHeaders = "Access-Control-Allow-Origin")
    @GetMapping("/success_logout")
    public ResponseEntity<Object> successLogout(){
        System.out.println("come here");return authenticationService.logout();
    }
}
