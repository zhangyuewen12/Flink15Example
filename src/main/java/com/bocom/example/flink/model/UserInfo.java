package com.bocom.example.flink.model;

/**
 * UserInfo
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
public class UserInfo {
    private String userName;
    private String phoneNumber;
    private String address;

    // Getters and Setters
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
