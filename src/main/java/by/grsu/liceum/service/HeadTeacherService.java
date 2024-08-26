package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface HeadTeacherService {
    GroupFullDto findGroupById(long groupId);
    List<AccountShortcutDto> findAll(long headTeacherAccountId);
    AccountFullDto findById(long id);
    TransactionDto addRating(RatingDto ratingDto);
    TransactionDto getRating(RatingDto ratingDto);
}
