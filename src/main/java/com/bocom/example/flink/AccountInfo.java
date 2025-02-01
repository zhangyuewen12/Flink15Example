package com.bocom.example.flink;

/**
 * AccountInfo
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
public class AccountInfo {
    private String cardAccount;
    private String accountName;
    private String accountType;

    // Getters and Setters
    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
}
