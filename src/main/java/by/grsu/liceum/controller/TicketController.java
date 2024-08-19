package by.grsu.liceum.controller;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public List<TicketShortcutDto> findAll(){
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public TicketFullDto findById(@PathVariable("id") long id){
        return ticketService.findById(id);
    }

    @PostMapping("/set")
    public TicketFullDto setTicketToTheAccount(@RequestBody SetTicketDto ticketDto){
        return ticketService.setTicketToTheAccount(ticketDto);
    }

    @PostMapping("/{id}")
    public void rollTicketBack(@PathVariable("id") long id){
        ticketService.rollTicketBack(id);
    }

    @PostMapping("/code")
    public void readCode(@RequestBody TicketReadCodeDto readCodeDto){
        ticketService.readCode(readCodeDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        ticketService.deleteById(id);
    }
}
