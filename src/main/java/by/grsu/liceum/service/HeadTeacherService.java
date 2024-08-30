package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;
import java.util.UUID;

public interface HeadTeacherService {
    List<GroupShortcutDto> findAll(UUID institutionId);
    TransactionDto addRating(UUID institutionId, RatingDto ratingDto);
    TransactionDto getRating(UUID institutionId, RatingDto ratingDto);
}
//todo раскидать по разным, порешать момент с addRating методами и попытаться вынести их в абстракцию