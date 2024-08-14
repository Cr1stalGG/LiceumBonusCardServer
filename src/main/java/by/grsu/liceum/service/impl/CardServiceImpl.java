package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.dto.mapper.CardDtoMapper;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.exception.CardWithIdNotFoundException;
import by.grsu.liceum.repository.CardRepository;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.utils.Generator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    private final CardRepository cardRepository;

    @Override
    public CardDto findById(long id) {
        Card card = Optional.ofNullable(cardRepository.findById(id))
                .orElseThrow(() -> new CardWithIdNotFoundException(id));

        return CardDtoMapper.convertEntityToDto(card);
    }

    @Override
    public List<CardDto> findAll() {
        return cardRepository.findAll().stream()
                .map(CardDtoMapper::convertEntityToDto)
                .toList();
    }

    @Override
    public Card generateCard() {
        Card card = Card.builder()
                .number(Generator.generateCardNumber())
                .balance(0)
                .sentTransactions(new ArrayList<>())
                .takenTransactions(new ArrayList<>())
                .build();

        //todo test
        cardRepository.save(card);

        return card;
    }

    @Override
    public void deleteById(long id) {
        findById(id);

        cardRepository.deleteById(id);
    }
}
