package com.test.cnss.myProject.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.cnss.myProject.model.Account;
import com.test.cnss.myProject.repository.AccountRepository;
import com.test.cnss.myProject.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public Account createAccount(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public Account getAccountById(Long id) {
        
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            return accountOptional.get();
        }
        return null;
    }
    
    @Override
    public Account updateAccount(Long id, Account account) {
        Account existingAccount = null;
        Optional<Account> accountOptional = accountRepository.findById(id);
        if (accountOptional.isPresent()) {
            existingAccount = accountOptional.get();
            existingAccount.setStatus(account.getStatus());
            existingAccount.setAccountType(account.getAccountType());
            
            existingAccount.setBalance(account.getBalance());
            
            return accountRepository.save(existingAccount);
        }
        return null;
    }
    
    @Override
    public void closeAccount(Long id) {
        Account account = getAccountById(id);
        account.setStatus("CLOSED");
        accountRepository.save(account);
    }
    
    @Override
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}