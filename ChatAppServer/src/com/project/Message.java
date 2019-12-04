package com.project;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Message implements Serializable {

    private static final long serialVersionUID = 1234567L;

    private final String body;

    private final User user;

    private LocalDateTime date;


    public Message(String body, User user) {
        Objects.requireNonNull(body, "The body of message is null.");
        Objects.requireNonNull(user, "The user of message is null.");
        this.body = body;
        this.user = user;
    }

    public String getBody() {
        return body;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Message{" +
                "body='" + body + '\'' +
                ", user=" + user +
                ", date=" + date +
                '}';
    }
}
