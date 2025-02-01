package com.bocom.example.flink;

/**
 * EnrichedTransaction
 *
 * @author zhangyuewen
 * @since 2025/2/1
 **/
// 定义 EnrichedTransaction 类，用于合并交易记录和账户信息
public  class EnrichedTransaction {
    private TransactionRecord transaction;
    private UserInfo userInfo;

    public EnrichedTransaction(TransactionRecord transaction, UserInfo userInfo) {
        this.transaction = transaction;
        this.userInfo = userInfo;
    }

    // Getters and Setters
    public TransactionRecord getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionRecord transaction) {
        this.transaction = transaction;
    }

    public UserInfo getAccountInfo() {
        return userInfo;
    }

    public void setAccountInfo(UserInfo accountInfo) {
        this.userInfo = accountInfo;
    }
}
