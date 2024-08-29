package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    Ticket findById(long id);
    List<Ticket> findAll();
    List<Ticket> findAllByBonus_Institution_Id(long institutionId);
    boolean existsByCode(String code);
}
