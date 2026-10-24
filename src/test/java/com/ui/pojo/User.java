package com.ui.pojo;

public class User {
    private String emailAddress;
    private String password;
    private String name;
    private String errorMessage;

    public User(String emailAddress, String password , String name, String errorMessage) {
        super();
        this.emailAddress = emailAddress;
        this.password = password;
        this.name = name;
        this.errorMessage = errorMessage;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public String toString() {
        return "User [emailAddress=" + emailAddress + ", password=" + password + ", Name=" + name + "]";
    }
}
