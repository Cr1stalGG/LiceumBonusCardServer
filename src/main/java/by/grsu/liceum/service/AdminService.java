package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface AdminService {
    TransactionDto addRating(UUID institutionId, RatingDto ratingDto);
    TransactionDto getRating(UUID institutionId, RatingDto ratingDto);
    List<AdminShortcutDto> findAllAdmins();
    List<AdminShortcutDto> findAllAdminsByCity(String city);
    AdminFullDto findAdminById(UUID id);
    AdminFullDto createAdmin(UUID institutionId);
    AdminFullDto regeneratePassword(UUID institutionId, UUID adminId);
    //AccountFullDto updateAdmin(UUID id, AccountUpdateDto updateDto); todo а в каком случае это вообще может пригодиться?
    void deleteAdminById(UUID id);
}
