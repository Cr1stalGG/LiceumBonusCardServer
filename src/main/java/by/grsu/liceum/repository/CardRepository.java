package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    Card findById(long id);
    List<Card> findAll();
    List<Card> findAllByAccount_Institution_Id(long institutionId);
}
