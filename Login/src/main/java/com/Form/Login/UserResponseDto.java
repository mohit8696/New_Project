package com.Form.Login;

public class UserResponseDto {
    private String userId;
    private String name;
    private String email;
    private String userType;

    public String getUserId() {
        return userId;
    }

    public UserResponseDto(String userId, String name, String email, String userType) {
        this.userId = userId;
        this.name = name;
        this.email = email;
        this.userType=userType;

    }

    public UserResponseDto() {
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
