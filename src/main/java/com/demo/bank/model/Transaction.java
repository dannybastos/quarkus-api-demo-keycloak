package com.demo.bank.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
@Entity
@Table(name = "TB_TRANSACTION")
public class Transaction {
    @Id
    @GeneratedValue(generator = "SQ_TRANSACTION", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "SQ_TRANSACTION", sequenceName = "SQ_TRANSACTION", initialValue = 1, allocationSize = 1)
    private Long id;
    @CreationTimestamp
    private LocalDateTime dateTime;
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;
    @ManyToOne(optional = false)
    private Account account;
    @Column(scale = 12, precision = 2)
    private Float amount;

    public Transaction(final TransactionType transactionType, final Account account, final Float amount) {
        this.transactionType = transactionType;
        this.account = account;
        this.amount = amount;
    }


    public Transaction() {
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
    public Float getAmount() {
        return amount;
    }
    public void setAmount(Float amount) {
        this.amount = amount;
    }
    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }
}
