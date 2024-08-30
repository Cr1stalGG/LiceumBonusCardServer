package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group findById(UUID id);
    List<Group> findAll();
    List<Group> findAllByMembers_Institution_Id(UUID institutionId);
    void deleteById(UUID id);
}
