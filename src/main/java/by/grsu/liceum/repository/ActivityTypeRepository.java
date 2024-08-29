package by.grsu.liceum.repository;

import by.grsu.liceum.entity.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityTypeRepository extends JpaRepository<ActivityType, Long> {
    ActivityType findById(long id);
    List<ActivityType> findAll();
    List<ActivityType> findAllByInstitution_Id(long institutionId);
}
