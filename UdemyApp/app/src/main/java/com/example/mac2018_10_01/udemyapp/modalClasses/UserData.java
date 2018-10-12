package com.example.mac2018_10_01.udemyapp.modalClasses;

public class UserData {

    String userName, email, loginType;

    public UserData(){}

    public UserData(String userName, String email, String loginType) {
        this.userName = userName;
        this.email = email;
        this.loginType = loginType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
