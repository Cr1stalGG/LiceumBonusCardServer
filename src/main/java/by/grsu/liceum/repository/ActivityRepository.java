package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findById(UUID id);
    List<Activity> findAll();
    List<Activity> findAllByActivityType_Institution_Id(UUID institutionId);
    void deleteById(UUID id);
}
