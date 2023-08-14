package com.leon.gptclone.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@Entity
@Table(name = "chat_lines")
public class ChatLine {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="chat_line_id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @Lob
    @Column(length = 1000)
    private String question;

    @Lob
    @Column(length = 1000)
    private String response;
    @CreationTimestamp
    private Timestamp timestamp;

    public ChatLine() {

    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getQuestion() {
        return question;
    }

    public String getResponse() {
        return response;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public ChatLine(User user, String question, String response) {
        this.user = user;
        this.question = question;
        this.response = response;
    }
}
