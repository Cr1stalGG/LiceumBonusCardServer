package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
    Bonus findById(long id);
    List<Bonus> findAll();
}
