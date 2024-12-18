package by.grsu.liceum.repository;

import by.grsu.liceum.entity.SolvedActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RedisHash
@Repository
public interface SolvedActivityRepository extends JpaRepository<SolvedActivity, Long> {
    List<SolvedActivity> findAll();
    List<SolvedActivity> findAllByActivity_ActivityType_Institution_Id(UUID institutionId);
    List<SolvedActivity> findAllByAccount_Id(UUID accountId);
    List<SolvedActivity> findAllByActivity_Id(UUID activityId);
    SolvedActivity findById(UUID id);
    void deleteById(UUID id);
}