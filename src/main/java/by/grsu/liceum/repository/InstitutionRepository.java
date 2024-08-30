package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Institution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface InstitutionRepository extends JpaRepository<Institution, Long> {
    Institution findById(UUID id);
    List<Institution> findAll();
    List<Institution> findAllByCity(String city);
    List<Institution> findAllByNameLike(String name);
    void deleteById(UUID id);
}
