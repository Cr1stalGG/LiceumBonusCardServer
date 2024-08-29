package by.grsu.liceum.controller;

import by.grsu.liceum.dto.card.CardDto;
import by.grsu.liceum.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/cards")
@RequiredArgsConstructor
public class CardController {
    private final CardService cardService;

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public CardDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return cardService.findById(institutionId, id);
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public List<CardDto> findAll(@PathVariable("institutionId") long institutionId){
        return cardService.findAll(institutionId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public void deleteById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        cardService.deleteById(institutionId, id);
    }
}
