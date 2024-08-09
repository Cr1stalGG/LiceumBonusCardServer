package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;

public interface AdminService {
    AccountCreationDto createUserWithRole(AccountCreationDto creationDto);
    void deleteUser(long id);
    void addRating(long accountId, int value);
    void getRating(long accountId, int value);
    void createBonus(BonusCreationDto creationDto);
    //todo activity_type, activity
}
