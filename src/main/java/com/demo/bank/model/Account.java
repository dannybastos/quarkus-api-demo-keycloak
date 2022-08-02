package com.demo.bank.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.CreationTimestamp;

import javax.json.bind.annotation.JsonbNumberFormat;
import javax.persistence.*;
import java.time.LocalDateTime;

import static com.demo.bank.model.TransactionType.C;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_ACCOUNT")
    @SequenceGenerator(name="SQ_ACCOUNT", sequenceName = "SQ_ACCOUNT", initialValue = 1, allocationSize = 1)
    private Long accountNumber;
    @JsonbNumberFormat("#0.00")
    @Column(scale = 12, precision = 2)
    private Float balance = 0F;
    @CreationTimestamp
    private LocalDateTime createdDate;

    public void updateBalance(Float amount, final TransactionType transactionType) {
        Float newBalance = Float.valueOf(balance);
        newBalance += (C.equals(transactionType)) ? amount : (-1)*amount;
        if (newBalance < 0)
            throw new IllegalArgumentException("not enough funds");
        this.setBalance(newBalance);
    }

    public Account() {
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
