package by.grsu.liceum.service;

import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.transaction.TransactionDto;

import java.util.List;

public interface HeadTeacherService {
    List<GroupShortcutDto> findAll(long headTeacherAccountId);
    TransactionDto addRating(RatingDto ratingDto);
    TransactionDto getRating(RatingDto ratingDto);
}
//todo раскидать по разным, порешать момент с addRating методами и попытаться вынести их в абстракцию