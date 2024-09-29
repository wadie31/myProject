package com.test.cnss.myProject.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;

import com.test.cnss.myProject.model.Transaction;
import com.test.cnss.myProject.repository.TransactionRepository;
import com.test.cnss.myProject.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {
    
    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    
    @Autowired
    private TransactionRepository transactionRepository;
    
    @Override
    public Transaction initiateTransaction(Transaction transaction) {
        transaction.setStatus("PENDING");
        return transactionRepository.save(transaction);
    }
    
    @Override
    public Transaction processTransaction(Long id, Transaction transaction) {
        Transaction transactionDb = getTransactionById(id).get();
        if (!"PENDING".equals(transactionDb.getStatus())) {
            throw new IllegalStateException("Only pending transactions can be processed");
        }
        
        if (transactionDb.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            prepareModificationFields(transaction, transactionDb, "FAILED");
            return transactionRepository.save(transaction);
            
        }
        prepareModificationFields(transaction, transactionDb, "COMPLETED");
        return transactionRepository.save(transactionDb);
    }
    
    private void prepareModificationFields(Transaction transaction, Transaction transactionDb, String status) {
        transactionDb.setCurrency(transaction.getCurrency());
        transactionDb.setAmount(transaction.getAmount());
        transactionDb.setDescription(transaction.getDescription());
        transactionDb.setAccount(transaction.getAccount());
        transactionDb.setPaymentMethod(transaction.getPaymentMethod());
        transactionDb.setStatus(status);
    }
    
    @Override
    public Optional<Transaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
    
    @Override
    public void cancelTransaction(Long id) {
        Transaction transaction = getTransactionById(id).get();
        if ("PENDING".equals(transaction.getStatus())) {
            transaction.setStatus("CANCELLED");
            transactionRepository.save(transaction);
        } else {
            throw new IllegalStateException("Only pending transactions can be cancelled");
        }
    }
    
    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }
    
    public static boolean isEmpty(@Nullable Object str) {
        return (str == null || "".equals(str) || "null".equals(str));
    }
    
    @Override
    public List<Transaction> getTransactionsWithFilters(Long id, Long customerId, Long accountId, String status, String dateDebut,
            String dateFin) throws ParseException {
        return transactionRepository.findAllWithFilters(id, customerId, accountId, status,
                isEmpty(dateDebut) ? null : formatter.parse(dateDebut), isEmpty(dateFin) ? null : formatter.parse(dateFin));
    }
    
}
