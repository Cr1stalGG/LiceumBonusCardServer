package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RedisHash
@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findById(UUID id);
    List<Card> findAll();
    List<Card> findAllByAccount_Institution_Id(UUID institutionId);
    void deleteById(UUID id);
}
