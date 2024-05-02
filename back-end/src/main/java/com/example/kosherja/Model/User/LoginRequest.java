package com.example.kosherja.Model.User;

import lombok.Getter;

@Getter
public class LoginRequest {
    public String getUsername() {
        return username;
    }

    private String username;

    public String getPassword() {
        return password;
    }

    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}