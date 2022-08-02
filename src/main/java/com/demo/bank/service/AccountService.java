package com.demo.bank.service;

import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.model.Account;
import com.demo.bank.model.Transaction;
import com.demo.bank.model.TransactionType;

import java.util.List;

public interface AccountService {
    void create(final Account account);
    void doTransfer(final Long fromAccountNumber
            , final Long toAccountNumber
            , final Float amount
            , final TransactionType transactionType);

    List<TransactionDTO> listTransactions(final Long accountNumber);

    List<Account> listAll();
}
