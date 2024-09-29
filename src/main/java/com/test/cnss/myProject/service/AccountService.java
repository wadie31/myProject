package com.test.cnss.myProject.service;

import java.util.List;

import com.test.cnss.myProject.model.Account;

public interface AccountService {
    Account createAccount(Account account);
    
    Account getAccountById(Long id);
    
    Account updateAccount(Long id, Account account);
    
    void closeAccount(Long id);
    
    List<Account> getAllAccounts();
}