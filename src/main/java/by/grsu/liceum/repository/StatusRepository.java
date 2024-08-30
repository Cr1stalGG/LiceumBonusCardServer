package by.grsu.liceum.repository;

import by.grsu.liceum.entity.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface StatusRepository extends JpaRepository<TransactionStatus, Long> {
    TransactionStatus findById(UUID id);
    TransactionStatus findByName(String name);
    List<TransactionStatus> findAll();
}
