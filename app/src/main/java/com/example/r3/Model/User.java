package com.example.r3.Model;

public class User {
    private String UserID;
    private String UserType;
    private String UserName;
    private String Password;

    public User(String UserID, String UserType,  String UserName, String password){
        UserID= UserID;
        UserType = UserType;
        UserName = UserName;
        Password = password;
    }

    public String getUserID() {
        return UserID;
    }
    public String getUserType() {
        return UserType;
    }

    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }
}
