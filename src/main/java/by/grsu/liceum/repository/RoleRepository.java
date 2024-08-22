package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Role;
import by.grsu.liceum.entity.enums.RoleConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(RoleConstant roleConstant);
    Role findById(long id);
    List<Role> findAll();
}
