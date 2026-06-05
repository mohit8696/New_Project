package com.Form.Login;

public class LoginResponse {
    private String userId;
    private String message;
    private String email;
    private String name;


    public LoginResponse(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public LoginResponse(String userId, String email, String name) {
        this.userId = userId;
        this.email = email;
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
