package by.grsu.liceum.controller;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{id}")
    public CardDto findById(@PathVariable("id") long id){
        return cardService.findById(id);
    }

    @GetMapping
    public List<CardDto> findAll(){
        return cardService.findAll();
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        cardService.deleteById(id);
    }
}
