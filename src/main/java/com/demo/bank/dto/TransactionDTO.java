package com.demo.bank.dto;

import com.demo.bank.model.Transaction;
import com.demo.bank.model.TransactionType;

import java.time.LocalDateTime;

public class TransactionDTO {

    private Long id;
    private Long fromAccount;
    private Float amount;

    private TransactionType transactionType;
    private LocalDateTime dateTime;

    public TransactionDTO() {
    }

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.fromAccount = transaction.getAccount().getAccountNumber();
        this.dateTime = transaction.getDateTime();
        this.amount = transaction.getAmount();
        this.transactionType = transaction.getTransactionType();
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getFromAccount() {
        return fromAccount;
    }
    public void setFromAccount(Long fromAccount) {
        this.fromAccount = fromAccount;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
