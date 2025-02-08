package com.bocom.example.flink.model;

/**
 * com.bocom.example.flink.model.TransactionRecord
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
public class TransactionRecord {
    private String cardAccount;
    private double amount;

    // Getters and Setters
    public String getCardAccount() {
        return cardAccount;
    }

    public void setCardAccount(String cardAccount) {
        this.cardAccount = cardAccount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
