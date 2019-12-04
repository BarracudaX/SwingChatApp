package com.project;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private final String username;

    public User(String username) {
        Objects.requireNonNull(username, "The username is null");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                '}';
    }
}
