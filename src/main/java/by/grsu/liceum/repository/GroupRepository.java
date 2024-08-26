package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findById(long id);
    List<Group> findAll();
    List<Group> findAllByMembers_Institution_Id(long institutionId);
}
