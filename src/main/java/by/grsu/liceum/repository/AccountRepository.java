package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Account;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    Account findById(UUID id);

    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    List<Account> findAll();

    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    List<Account> findAllByInstitution_Id(UUID institutionId);

    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    List<Account> findAllByRoles_Name(String name);

    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    List<Account> findAllByRoles_NameAndInstitution_City(String name, String city);

    @EntityGraph(value = "account-with-roles", type = EntityGraph.EntityGraphType.LOAD)
    Account findByLogin(String login);
    void deleteById(UUID id);

}