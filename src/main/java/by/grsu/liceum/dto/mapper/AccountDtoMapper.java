package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.ticket.TicketShortcutDto;
import by.grsu.liceum.dto.utils.GeneratorLoginDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Group;
import by.grsu.liceum.entity.Ticket;
import by.grsu.liceum.exception.NullableAccountCreationDtoException;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class AccountDtoMapper {
    public static AccountFullDto convertEntityToFullDto(Account source){
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::buildFullDto)
                .orElse(null);
    }

    public static AccountShortcutDto convertEntityToShortcutDto(Account source) {
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static Account convertDtoToEntity(AccountCreationDto source){
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::buildEntity)
                .orElseThrow(NullableAccountCreationDtoException::new);
    }

    public static AccountCreationResponse convertEntityToCreationResponse(Account source) {
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::buildCreationResponse)
                .orElse(null);
    }
    
    public static GeneratorLoginDto convertCreationDtoToGeneratorDto(AccountCreationDto source){
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::buildGeneratorDto)
                .orElse(null);//todo mb exception with 401 status
    }

    private static GeneratorLoginDto buildGeneratorDto(AccountCreationDto source) {
        return GeneratorLoginDto.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .yearOfStartOfStudies(source.getYearOfStartOfStudies())
                .build();
    }

    private static AccountCreationResponse buildCreationResponse(Account source) {
        return AccountCreationResponse.builder()
                .uuid(source.getId())
                .login(source.getLogin())
                .password(source.getPassword())
                .card(CardDtoMapper.convertEntityToDto(source.getCard()))
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }

    private static Account buildEntity(AccountCreationDto source) {
        return Account.builder()
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .ownedGroups(new ArrayList<>())
                .otherGroups(new ArrayList<>())
                .tickets(new ArrayList<>())
                .build();
    }

    private static AccountFullDto buildFullDto(Account source) {
        return AccountFullDto.builder()
                .uuid(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .card(CardDtoMapper.convertEntityToDto(source.getCard()))
                .tickets(buildTickets(source.getTickets()))
                .ownedGroups(buildGroups(source.getOwnedGroups()))
                .otherGroups(buildGroups(source.getOtherGroups()))
                .build();
    }

    private AccountShortcutDto buildShortcutDto(Account source){
        return AccountShortcutDto.builder()
                .uuid(source.getId())
                .firstName(source.getFirstName())
                .lastName(source.getLastName())
                .fatherName(source.getFatherName())
                .phoneNumber(source.getPhoneNumber())
                .build();
    }

    private static List<TicketShortcutDto> buildTickets(List<Ticket> source){
        if(source == null)
            return new ArrayList<>();

        return source.stream()
                .map(TicketDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    private static List<GroupShortcutDto> buildGroups(List<Group> source){
        if(source == null)
            return new ArrayList<>();

        return source.stream()
                .map(GroupDtoMapper::convertEntityToShortcutDto)
                .toList();
    }
}
