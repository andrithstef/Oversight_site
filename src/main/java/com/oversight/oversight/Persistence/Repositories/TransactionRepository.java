package com.oversight.oversight.Persistence.Repositories;

import com.oversight.oversight.Persistence.Entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction save(Transaction transaction);
    void delete(Transaction transaction);

    List<Transaction> findAll();

    Transaction findByID(long id);
}
