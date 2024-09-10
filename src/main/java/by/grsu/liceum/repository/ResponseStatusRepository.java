package by.grsu.liceum.repository;

import by.grsu.liceum.entity.ResponseStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@RedisHash
@Repository
public interface ResponseStatusRepository extends JpaRepository<ResponseStatus, Long> {
    ResponseStatus findById(UUID id);
    ResponseStatus findByName(String name);
    List<ResponseStatus> findAll();
}
