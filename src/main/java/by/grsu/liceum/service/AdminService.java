package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface AdminService {
    TransactionDto addRating(RatingDto ratingDto);
    TransactionDto getRating(RatingDto ratingDto);
    List<AccountShortcutDto> findAllAdmins();
    List<AccountShortcutDto> findAllAdminsByCity(String city);
    AccountFullDto findAdminById(long id);
    AccountFullDto createAdmin(long institutionId);
    //AccountFullDto updateAdmin(long id, AccountUpdateDto updateDto); todo а в каком случае это вообще может пригодиться?
    void deleteAdminById(long id);
}
