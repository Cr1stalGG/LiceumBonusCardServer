package by.grsu.liceum.service;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;

import java.util.List;

public interface TicketService {
    List<TicketShortcutDto> findAll(long institutionId);
    TicketFullDto findById(long institutionId, long id);
    TicketFullDto setTicketToTheAccount(long institutionId, SetTicketDto ticketDto);
    void rollTicketBack(long institutionId, long id);
    void readCode(long institutionId, TicketReadCodeDto readCodeDto);
    void deleteById(long institutionId, long id);
}
