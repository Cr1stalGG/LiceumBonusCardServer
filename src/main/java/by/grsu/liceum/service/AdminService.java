package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface AdminService {
    TransactionDto addRating(long institutionId, RatingDto ratingDto);
    TransactionDto getRating(long institutionId, RatingDto ratingDto);
    List<AdminShortcutDto> findAllAdmins();
    List<AdminShortcutDto> findAllAdminsByCity(String city);
    AdminFullDto findAdminById(long id);
    AdminFullDto createAdmin(long institutionId);
    AdminFullDto regeneratePassword(long institutionId, long adminId);
    //AccountFullDto updateAdmin(long id, AccountUpdateDto updateDto); todo а в каком случае это вообще может пригодиться?
    void deleteAdminById(long id);
}
