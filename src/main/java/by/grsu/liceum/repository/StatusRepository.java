package by.grsu.liceum.repository;

import by.grsu.liceum.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<TransactionStatus, Long> {
    TransactionStatus findById(long id);
    TransactionStatus findByName(String name);
    List<TransactionStatus> findAll();
}
