package com.leon.gptclone.model.response;

import com.leon.gptclone.model.ChatLine;

import java.util.List;

public class ChatListResponse {
    List<ChatLine> chatLines;
    String code;
    String message;

    public void setChatLines(List<ChatLine> chatLines) {
        this.chatLines = chatLines;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ChatLine> getChatLines() {
        return chatLines;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public ChatListResponse(List<ChatLine> chatLines, String code, String message) {
        this.chatLines = chatLines;
        this.code = code;
        this.message = message;
    }
}
