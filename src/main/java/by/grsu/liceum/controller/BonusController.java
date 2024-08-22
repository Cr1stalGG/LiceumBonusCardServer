package by.grsu.liceum.controller;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bonuses")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService bonusService;

    @GetMapping
    public List<BonusShortcutDto> findAll(){
        return bonusService.findAll();
    }

    @GetMapping("/{id}")
    public BonusFullDto findById(@PathVariable("id") long id){
        return bonusService.findById(id);
    }

    @PostMapping
    @Secured(value = "ROLE_ADMIN")
    public BonusFullDto createBonus(@RequestBody BonusCreationDto creationDto){
        return bonusService.createBonus(creationDto);
    }

    @PostMapping("/buy")
    public TicketFullDto buyBonus(@RequestBody BonusBuyDto buyDto){
        return bonusService.buyBonus(buyDto);
    }

    @DeleteMapping("/{id}")
    @Secured(value = "ROLE_ADMIN")
    public void deleteById(@PathVariable("id") long id){
        bonusService.deleteById(id);
    }
}
