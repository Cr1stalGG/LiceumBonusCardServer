package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountCreationDto;
import by.grsu.liceum.dto.account.AccountCreationResponse;
import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.mapper.AccountDtoMapper;
import by.grsu.liceum.dto.mapper.ImageDtoMapper;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Card;
import by.grsu.liceum.entity.Image;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.entity.Response;
import by.grsu.liceum.entity.ResponseStatus;
import by.grsu.liceum.entity.Role;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.InvalidRoleNameException;
import by.grsu.liceum.exception.ResponseStatusWithNameNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.ImageRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.repository.ResponseRepository;
import by.grsu.liceum.repository.ResponseStatusRepository;
import by.grsu.liceum.repository.RoleRepository;
import by.grsu.liceum.service.AccountService;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.service.enums.ResponseStatusConstant;
import by.grsu.liceum.service.enums.RoleConstant;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@PropertySource("classpath:business_settings.properties")
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final CardService cardService;
    private final InstitutionRepository institutionRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageRepository imageRepository;
    private final ResponseStatusRepository responseStatusRepository;
    private final ResponseRepository responseRepository;

    @Override
    public AccountFullDto findById(UUID institutionId, UUID id) {
        Account account = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return AccountDtoMapper.convertEntityToFullDto(account);
    }

    @Override
    public List<AccountShortcutDto> findAll(UUID institutionId) {
        return accountRepository.findAllByInstitution_Id(institutionId).stream()
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    @Transactional
    public AccountCreationResponse createUserWithRole(UUID institutionId, AccountCreationDto creationDto) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        Account account = AccountDtoMapper.convertDtoToEntity(creationDto);

        String password = Generator.generatePassword(RoleConstant.ROLE_USER.getValue());

        account.setLogin(Generator.generateLogin(AccountDtoMapper.convertCreationDtoToGeneratorDto(creationDto)));
        account.setPassword(bCryptPasswordEncoder.encode(password));
        account.setInstitution(institution);
        account.setRoles(new ArrayList<>());

        accountRepository.save(account);

        for(String roleConstant : creationDto.getRoleNames()){
            Role role = Optional.ofNullable(roleRepository.findByName(roleConstant.toUpperCase()))
                    .orElseThrow(() -> new InvalidRoleNameException(roleConstant));

            role.getAccounts().add(account);
            account.getRoles().add(role);
        }

        Card card = cardService.generateCard();

        account.setCard(card);

        institution.getAccounts().add(account);

        AccountCreationResponse response = AccountDtoMapper.convertEntityToCreationResponse(account);
        response.setPassword(password);

        return response;
    }

    @Override
    @Transactional
    public AccountCreationResponse regeneratePassword(UUID institutionId, UUID accountId) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        String password = Generator.generatePassword(RoleConstant.ROLE_USER.getValue());

        account.setPassword(bCryptPasswordEncoder.encode(password));

        AccountCreationResponse response = AccountDtoMapper.convertEntityToCreationResponse(account);
        response.setPassword(password);

        return response;
    }

    @Override
    @Transactional
    public AccountFullDto setImage(UUID institutionId, UUID accountId, ImageCreationDto creationDto) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        Image image = ImageDtoMapper.convertDtoToEntity(creationDto);
        imageRepository.save(image);

        account.setImage(image);

        return AccountDtoMapper.convertEntityToFullDto(account);
    }

    //@Scheduled(cron = "${scheduler.cron.interval.accounts}") todo real
    @Scheduled(fixedDelay = 120_000L) //todo test
    @Transactional
    public void accountsGradeUpOrDelete(){
        log.info("=======DELETE ALL ACCOUNTS GRADE OFF=======");

        List<Account> accounts = Optional.of(accountRepository.findAll())
                .orElse(new ArrayList<>());

        for(Account account: accounts)
           if(account.getRoles().stream().allMatch(x -> x.getName().equals(RoleConstant.ROLE_USER.getValue()))) {
               log.info("Account with id {} graded", account.getId());
               account.setGrade(account.getGrade() + 1);

               ResponseStatus responseStatus = Optional.ofNullable(responseStatusRepository.findByName(ResponseStatusConstant.RESPONSE_STATUS_ACCOUNT_GRADE_UP.getValue()))
                       .orElseThrow(() -> new ResponseStatusWithNameNotFoundException(ResponseStatusConstant.RESPONSE_STATUS_ACCOUNT_GRADE_UP.getValue()));

               Response response = Response.builder()
                       .message("grade up of account")
                       .responseStatus(responseStatus)
                       .timeOfResponse(new Date(System.currentTimeMillis()))
                       .account(account)
                       .build();

               responseRepository.save(response);

               responseStatus.getResponses().add(response);
               account.getResponses().add(response);

               if (account.getGrade() >= 12) {
                   log.info("Delete account with id {}", account.getId());

                   Account admin = accountRepository.findByRoles_NameAndInstitution_Id(RoleConstant.ROLE_ADMIN.getValue(), account.getInstitution().getId()).iterator().next();
                   ResponseStatus deletedResponseStatus = Optional.ofNullable(responseStatusRepository.findByName(ResponseStatusConstant.RESPONSE_STATUS_ACCOUNT_DELETED.getValue()))
                                   .orElseThrow(() -> new ResponseStatusWithNameNotFoundException(ResponseStatusConstant.RESPONSE_STATUS_ACCOUNT_DELETED.getValue()));

                   Response deletedResponse = Response.builder()
                           .message(String.format("Account with lastname %s deleted bcs of 12 grade", account.getLastName()))
                           .responseStatus(deletedResponseStatus)
                           .account(admin)
                           .timeOfResponse(new Date(System.currentTimeMillis()))
                           .build();

                   responseRepository.save(deletedResponse);

                   deletedResponseStatus.getResponses().add(deletedResponse);
                   admin.getResponses().add(deletedResponse);

                   accountRepository.deleteById(account.getId());
               }
           }
        //todo add responses to dto
        log.info("==========================================");
    }

    @Override
    public void deleteById(UUID institutionId, UUID id) {
        Account account = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        accountRepository.deleteById(id);
    }
}
