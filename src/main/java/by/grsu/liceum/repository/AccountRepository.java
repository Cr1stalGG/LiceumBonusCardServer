package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(UUID id);
    List<Account> findAll();
    List<Account> findAllByInstitution_Id(UUID institutionId);
    List<Account> findAllByRoles_Name(String name);
    List<Account> findAllByRoles_NameAndInstitution_City(String name, String city);
    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    Account findByLogin(String login);
    void deleteById(UUID id);

}