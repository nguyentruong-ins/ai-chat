package com.leon.gptclone.model.response;

public class AIResponse {
    private String response;
    private String code;
    private String message;

    public String getResponse() {
        return response;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public AIResponse(String response, String code, String message) {
        this.response = response;
        this.code = code;
        this.message = message;
    }
}
