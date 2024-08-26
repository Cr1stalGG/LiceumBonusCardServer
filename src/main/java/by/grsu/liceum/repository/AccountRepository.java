package by.grsu.liceum.repository;

import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.enums.RoleConstant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);
    List<Account> findAll();
    List<Account> findAllByRoles_Name(RoleConstant name);
    List<Account> findAllByRoles_NameAndInstitution_City(RoleConstant name, String city);
    Account findByLogin(String login);
    void deleteById(long id);
}