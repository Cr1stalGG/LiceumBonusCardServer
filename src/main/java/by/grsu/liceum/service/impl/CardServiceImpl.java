package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.dto.mapper.CardDtoMapper;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.repository.CardRepository;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public CardDto findById(UUID institutionId, UUID id) {
        Card card = Optional.ofNullable(cardRepository.findById(id))
                .orElseThrow(() -> new CardWithIdNotFoundException(id));

        if (!card.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return CardDtoMapper.convertEntityToDto(card);
    }

    @Override
    public List<CardDto> findAll(UUID institutionId) {
        return cardRepository.findAllByAccount_Institution_Id(institutionId).stream()
                .map(CardDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public Card generateCard() {
        Card card = Card.builder()
                .number(Generator.generateCardNumber())
                .balance(0)
                .transactions(new ArrayList<>())
                .build();

        return cardRepository.save(card);
    }

    @Override
    public void deleteById(UUID institutionId, UUID id) {
        Card card = Optional.ofNullable(cardRepository.findById(id))
                .orElseThrow(() -> new CardWithIdNotFoundException(id));

        if (!card.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        cardRepository.deleteById(id);
    }
}
