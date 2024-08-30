package by.grsu.liceum.service;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.entity.Card;

import java.util.List;
import java.util.UUID;

public interface CardService {
    CardDto findById(UUID institutionId, UUID id);
    List<CardDto> findAll(UUID institutionId);
    Card generateCard();
    void deleteById(UUID institutionId, UUID id);
}
