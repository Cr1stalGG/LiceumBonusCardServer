package by.grsu.liceum.service;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.entity.Card;

import java.util.List;

public interface CardService {
    CardDto findById(long institutionId, long id);
    List<CardDto> findAll(long institutionId);
    Card generateCard();
    void deleteById(long institutionId, long id);
}
