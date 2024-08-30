package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();
    List<Transaction> findAllByTransactionStatusName(String statusConstant);
    List<Transaction> findAllByCard_Account_Institution_Id(UUID institutionId);
    Transaction findById(UUID id);
    void deleteById(UUID id);
}
