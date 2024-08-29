package by.grsu.liceum.controller;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.service.TicketService;
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
@RequestMapping("/api/v1/institutions/{institutionId}/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_SALE_UNIT', 'ROLE_SUPER_ADMIN', 'ROLE_HEAD_TEACHER')")
    public List<TicketShortcutDto> findAllByInstitution(@PathVariable("institutionId") long institutionId){
        return ticketService.findAll(institutionId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public TicketFullDto findById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        return ticketService.findById(institutionId, id);
    }

    @PostMapping("/set")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_TEACHER')")
    public TicketFullDto setTicketToTheAccount(@PathVariable("institutionId") long institutionId, @RequestBody SetTicketDto ticketDto){
        return ticketService.setTicketToTheAccount(institutionId, ticketDto);
    }

    @PostMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public void rollTicketBack(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        ticketService.rollTicketBack(institutionId, id);
    }

    @PostMapping("/code")
    @PreAuthorize("hasAuthority('ROLE_SALE_UNIT')")
    public void readCode(@PathVariable("institutionId") long institutionId, @RequestBody TicketReadCodeDto readCodeDto){
        ticketService.readCode(institutionId, readCodeDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public void deleteById(@PathVariable("institutionId") long institutionId, @PathVariable("id") long id){
        ticketService.deleteById(institutionId, id);
    }
}
