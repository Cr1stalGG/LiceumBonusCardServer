package by.grsu.liceum.controller;

import by.grsu.liceum.dto.bonus.BonusBuyDto;
import by.grsu.liceum.dto.bonus.BonusCreationDto;
import by.grsu.liceum.dto.bonus.BonusFullDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.service.BonusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/bonuses")
@RequiredArgsConstructor
public class BonusController {
    private final BonusService bonusService;

    @GetMapping
    @PreAuthorize("isAuthenticated()")
    public List<BonusShortcutDto> findAll(@PathVariable("institutionId") long institutionId){
        return bonusService.findAllByInstitutionId(institutionId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public BonusFullDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return bonusService.findById(institutionId, id);
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SUPER_ADMIN', 'ROLE_HEAD_TEACHER')")
    public BonusFullDto createBonus(@PathVariable("institutionId") long institutionId, @RequestBody BonusCreationDto creationDto){
        return bonusService.createBonus(institutionId, creationDto);
    }

    @PostMapping("/buy")
    @PreAuthorize("isAuthenticated()")
    public TicketFullDto buyBonus(@PathVariable("institutionId") long institutionId, @RequestBody BonusBuyDto buyDto){
        return bonusService.buyBonus(institutionId, buyDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public void deleteById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        bonusService.deleteById(institutionId, id);
    }
}
