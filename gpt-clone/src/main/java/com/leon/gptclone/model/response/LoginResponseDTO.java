package com.leon.gptclone.model.response;

import com.leon.gptclone.model.User;

public class LoginResponseDTO {
//    private User user;
    private String jwt;
    private String code;
    private String message;

    public LoginResponseDTO(){}

    public LoginResponseDTO(String jwt, String code, String message) {
        this.jwt = jwt;
        this.code = code;
        this.message = message;
    }

    public String getJwt() {
        return jwt;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
