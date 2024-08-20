package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Status;
import by.grsu.liceum.entity.enums.StatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    Status findById(long id);
    Status findByName(StatusConstant name);
    List<Status> findAll();
}
