package com.Form.Login;

public class LoginResponse {
    private String userId;
    private String message;
    private String userType;


    public LoginResponse(String userId, String userType) {
        this.userId = userId;
        this.userType= userType;
    }
    public LoginResponse(String userId, String userType, String message){
        this.userId = userId;
        this.userType= userType;
        this.message=message;
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

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
