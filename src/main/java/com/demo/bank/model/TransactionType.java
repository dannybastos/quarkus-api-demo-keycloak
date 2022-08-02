package com.demo.bank.model;

public enum TransactionType {
    C("Credit"),
    D("Debit");

    private String type;
    TransactionType(String type) {
        this.type = type;
    }
    public String getType() {
        return type;
    }
}
