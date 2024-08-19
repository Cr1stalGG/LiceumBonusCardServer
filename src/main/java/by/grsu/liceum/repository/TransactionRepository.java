package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();
    Transaction findById(long id);
}
