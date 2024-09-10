package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Bonus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RedisHash
@Repository
public interface BonusRepository extends JpaRepository<Bonus, Long> {
    Bonus findById(UUID id);
    List<Bonus> findAllByInstitution_Id(UUID institutionId);
    void deleteById(UUID id);
}
