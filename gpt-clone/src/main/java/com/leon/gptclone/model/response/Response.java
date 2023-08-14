package com.leon.gptclone.model.response;

public class Response {
    private String code;
    private String message;

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Response(String code, String message) {
        this.code = code;
        this.message = message;
    }
}
