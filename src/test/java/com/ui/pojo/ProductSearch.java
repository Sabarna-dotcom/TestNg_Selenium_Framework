package com.ui.pojo;

public class ProductSearch {
    private String emailAddress;
    private String password;
    private String searchKeyword;
    private String addToCartMessage;

    public String getAddToCartMessage() {
        return addToCartMessage;
    }

    public void setAddToCartMessage(String addToCartMessage) {
        this.addToCartMessage = addToCartMessage;
    }

    public ProductSearch(String emailAddress, String password, String searchKeyword, String addToCartMessage) {
        super();
        this.emailAddress = emailAddress;
        this.password = password;
        this.searchKeyword = searchKeyword;
        this.addToCartMessage = addToCartMessage;
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

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }
}
