package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findById(UUID id);
    List<Ticket> findAll();
    List<Ticket> findAllByBonus_Institution_Id(UUID institutionId);
    boolean existsByCode(String code);
    void deleteById(UUID id);
}
