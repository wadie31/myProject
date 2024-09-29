package com.test.cnss.myProject.service;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import com.test.cnss.myProject.model.Transaction;

public interface TransactionService {
    Transaction initiateTransaction(Transaction transaction);
    
    Transaction processTransaction(Long id, Transaction transaction);
    
    Optional<Transaction> getTransactionById(Long id);
    
    void cancelTransaction(Long id);
    
    List<Transaction> getAllTransactions();
    
    List<Transaction> getTransactionsWithFilters(Long id, Long customerId, Long accountId, String status, String dateDebut, String dateFin)
            throws ParseException;
}
