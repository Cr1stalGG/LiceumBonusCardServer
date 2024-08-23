package by.grsu.liceum.repository;

import by.grsu.liceum.entity.ResponseStatus;
import by.grsu.liceum.entity.enums.ResponseStatusConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseStatusRepository extends JpaRepository<ResponseStatus, Long> {
    ResponseStatus findById(long id);
    ResponseStatus findByName(ResponseStatusConstant name);
    List<ResponseStatus> findAll();
}
