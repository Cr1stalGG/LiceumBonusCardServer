package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {
    Activity findById(long id);
    List<Activity> findAll();
    List<Activity> finaAllByActivityType_Institution_Id(long institutionId);

}
