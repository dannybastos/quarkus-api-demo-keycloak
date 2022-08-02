package com.demo.bank.service.impl;

import com.demo.audit.model.Audit;
import com.demo.audit.repository.AuditRepository;
import com.demo.bank.dto.TransactionDTO;
import com.demo.bank.model.Account;
import com.demo.bank.model.Transaction;
import com.demo.bank.model.TransactionType;
import com.demo.bank.repository.AccountRepository;
import com.demo.bank.repository.TransactionRepository;
import com.demo.bank.service.AccountService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

import static com.demo.bank.model.TransactionType.C;
import static com.demo.bank.model.TransactionType.D;

@ApplicationScoped
public class AccountServiceImpl implements AccountService {

    @Inject
    TransactionRepository transactionRepository;
    @Inject
    AccountRepository accountRepository;
    @Inject
    AuditRepository auditRepository;

    @Transactional
    @Override
    public void create(Account account) {
        accountRepository.persist(account);
    }

    @Transactional
    @Override
    public void doTransfer(final Long fromAccountNumber
            , final Long toAccountNumber
            , final Float amount
            , final TransactionType transactionType) {
        try {
            Account fromAccount = accountRepository.findById(fromAccountNumber);
            Account toAccount = accountRepository.findById(toAccountNumber);
            Transaction transactionToAccount = new Transaction(transactionType, toAccount, amount);
            Transaction transactionFromAccount = new Transaction(transactionType.equals(C) ? D : C, fromAccount, amount);
            toAccount.updateBalance(amount, transactionType);
            if (transactionType.equals(C)) {
                fromAccount.updateBalance(amount, D);
            } else {
                fromAccount.updateBalance(amount, C);
            }
            transactionRepository.persist(transactionToAccount);
            transactionRepository.persist(transactionFromAccount);
            Audit audit = new Audit(transactionFromAccount, transactionToAccount);
            auditRepository.persist(audit);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    @Override
    public List<TransactionDTO> listTransactions(final Long accountNumber) {
        List<Transaction> transactionList = transactionRepository.list("account.accountNumber = ?1", accountNumber);
        return transactionList.stream().map(TransactionDTO::new).collect(Collectors.toList());
    }

    @Override
    public List<Account> listAll() {
        return accountRepository.listAll();
    }
}
