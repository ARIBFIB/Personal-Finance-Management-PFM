package com.example.pfm;

import java.util.Date;

public class User {
    String username;
    String fullName;
    Date sessionExpiryData;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Date getSessionExpiryData() {
        return sessionExpiryData;
    }

    public void setSessionExpiryDate(Date sessionExpiryData) {
        this.sessionExpiryData = sessionExpiryData;
    }
}
