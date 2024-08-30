package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    Response findById(UUID id);
    List<Response> findAll();
    List<Response> findByResponseStatusName(String name);
}
