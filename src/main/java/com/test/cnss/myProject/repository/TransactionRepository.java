package com.test.cnss.myProject.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.cnss.myProject.model.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccountId(Long accountId);
    
    List<Transaction> findByPaymentMethodId(Long paymentMethodId);
    
    List<Transaction> findByStatusAndAccountId(String string, Long accountId);
    
    @Query("SELECT t FROM Transaction t " + "JOIN t.account a " + "WHERE (:id IS NULL OR t.id = :id) "
            + "AND (:customerId IS NULL OR a.customer.id = :customerId) " + "AND (:accountId IS NULL OR a.id = :accountId) "
            + "AND (:status IS NULL OR t.status = :status) "
            + "AND ((:dateDebut IS NULL OR :dateFin IS NULL) OR (t.transactionDate BETWEEN :dateDebut AND :dateFin)) ")
    List<Transaction> findAllWithFilters(@Param("id") Long id, @Param("customerId") Long customerId, @Param("accountId") Long accountId,
            @Param("status") String status, @Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
    
    @Query(value = "SELECT MAX(id) FROM Transaction")
    Long findMaxId();
    
}