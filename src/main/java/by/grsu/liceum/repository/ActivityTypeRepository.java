package by.grsu.liceum.repository;

import by.grsu.liceum.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RedisHash
@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    ActivityType findById(UUID id);
    List<ActivityType> findAll();
    List<ActivityType> findAllByInstitution_Id(UUID institutionId);
    void deleteById(UUID id);
}
