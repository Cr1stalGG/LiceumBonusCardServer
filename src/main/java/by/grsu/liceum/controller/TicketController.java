package by.grsu.liceum.controller;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.service.TicketService;
import jakarta.validation.Valid;
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
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SALE_UNIT', 'ROLE_SUPER_ADMIN', 'ROLE_HEAD_TEACHER')")
    public List<TicketShortcutDto> findAllByInstitution(@PathVariable("institutionId") UUID institutionId){
        return ticketService.findAll(institutionId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public TicketFullDto findById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return ticketService.findById(institutionId, id);
    }

    @PostMapping("/set")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER')")
    public TicketFullDto setTicketToTheAccount(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid SetTicketDto ticketDto){
        return ticketService.setTicketToTheAccount(institutionId, ticketDto);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void rollTicketBack(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        ticketService.rollTicketBack(institutionId, id);
    }

    @PostMapping("/code")
    @PreAuthorize("hasAuthority('ROLE_SALE_UNIT')")
    public void readCode(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid TicketReadCodeDto readCodeDto){
        ticketService.readCode(institutionId, readCodeDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public void deleteById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        ticketService.deleteById(institutionId, id);
    }
}
