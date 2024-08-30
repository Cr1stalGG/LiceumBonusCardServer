package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String roleConstant);
    Role findById(UUID id);
    List<Role> findAll();
}
