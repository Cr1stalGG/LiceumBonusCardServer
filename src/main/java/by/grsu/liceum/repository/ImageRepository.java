package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findById(UUID id);
    List<Image> findAll();
}