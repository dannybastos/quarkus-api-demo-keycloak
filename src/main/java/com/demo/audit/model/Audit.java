package com.demo.audit.model;

import com.demo.bank.model.Transaction;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Audit {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SQ_AUDIT")
    @SequenceGenerator(name = "SQ_AUDIT", sequenceName = "SQ_AUDIT", initialValue = 1, allocationSize = 1)
    private Long id;
    @ManyToOne(optional = false)
    private Transaction transactionFrom;
    @ManyToOne(optional = false)
    private Transaction transactionTo;

    @CreationTimestamp
    private LocalDateTime dateTime;

    public Audit() {
    }

    public Audit(Transaction transactionFrom, Transaction transactionTo) {
        this.transactionFrom = transactionFrom;
        this.transactionTo = transactionTo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransactionFrom() {
        return transactionFrom;
    }

    public void setTransactionFrom(Transaction transactionFrom) {
        this.transactionFrom = transactionFrom;
    }

    public Transaction getTransactionTo() {
        return transactionTo;
    }

    public void setTransactionTo(Transaction transactionTo) {
        this.transactionTo = transactionTo;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
