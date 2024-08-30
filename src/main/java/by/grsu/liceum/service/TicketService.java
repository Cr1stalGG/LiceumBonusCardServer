package by.grsu.liceum.service;

import by.grsu.liceum.dto.ticket.SetTicketDto;
import by.grsu.liceum.dto.ticket.TicketFullDto;
import by.grsu.liceum.dto.ticket.TicketReadCodeDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;

import java.util.List;
import java.util.UUID;

public interface TicketService {
    List<TicketShortcutDto> findAll(UUID institutionId);
    TicketFullDto findById(UUID institutionId, UUID id);
    TicketFullDto setTicketToTheAccount(UUID institutionId, SetTicketDto ticketDto);
    void rollTicketBack(UUID institutionId, UUID id);
    void readCode(UUID institutionId, TicketReadCodeDto readCodeDto);
    void deleteById(UUID institutionId, UUID id);
}
