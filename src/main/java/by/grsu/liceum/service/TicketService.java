package by.grsu.liceum.service;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;

import java.util.List;

public interface TicketService {
    List<TicketShortcutDto> findAll();
    TicketFullDto findById(long id);
    TicketFullDto setTicketToTheAccount(SetTicketDto ticketDto);
    void rollTicketBack(long id);
    void readCode(TicketReadCodeDto readCodeDto);
    void deleteById(long id);
}
