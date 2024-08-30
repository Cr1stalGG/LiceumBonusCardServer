package by.grsu.liceum.repository;

import by.grsu.liceum.entity.SolvedActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolvedActivityRepository extends JpaRepository<SolvedActivity, Long> {
    List<SolvedActivity> findAll();
    List<SolvedActivity> findAllByActivity_ActivityType_Institution_Id(long institutionId);
    List<SolvedActivity> findAllByAccount_Id(long accountId);
    List<SolvedActivity> findAllByActivity_Id(long activityId);
    SolvedActivity findById(long id);
}
