package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.mapper.AccountDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidRatingAmountException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.service.AdminService;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.service.TransactionService;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@PropertySource("${classpath:business_settings.properties}")
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final InstitutionRepository institutionRepository;
    private final CardService cardService;

    @Value("${property.admin.rating.min_value}")
    private int minRatingValue;
    @Value("${property.admin.rating.max_value}")
    private int maxRatingValue;

    @Override
    @Transactional
    public TransactionDto addRating(RatingDto ratingDto) {
        if(ratingDto.getValue() < this.minRatingValue || ratingDto.getValue() > this.maxRatingValue)
            throw new InvalidRatingAmountException(this.minRatingValue, this.maxRatingValue, ratingDto.getValue());

        Account account = Optional.ofNullable(accountRepository.findById(ratingDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ratingDto.getAccountId()));

        account.getCard().setBalance(account.getCard().getBalance() + ratingDto.getValue());

        TransactionCreationDto creationDto = TransactionCreationDto.builder()
                .cardId(account.getCard().getId())
                .balance(ratingDto.getValue())
                .status("ADMIN_ACCRUAL_STATUS")
                .build();

        return transactionService.createTransaction(creationDto);
    }

    @Override
    @Transactional
    public TransactionDto getRating(RatingDto ratingDto) {
        if(ratingDto.getValue() < this.minRatingValue || ratingDto.getValue() > this.maxRatingValue)
            throw new InvalidRatingAmountException(this.minRatingValue, this.maxRatingValue, ratingDto.getValue());

        Account account = Optional.ofNullable(accountRepository.findById(ratingDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ratingDto.getAccountId()));

        if(account.getCard().getBalance() - ratingDto.getValue() <= 0)
            throw new NotEnoughBalanceError(ratingDto.getAccountId(), account.getCard().getBalance());

        account.getCard().setBalance(account.getCard().getBalance() - ratingDto.getValue());

        TransactionCreationDto creationDto = TransactionCreationDto.builder()
                .cardId(account.getCard().getId())
                .balance(ratingDto.getValue())
                .status("ADMIN_ACCRUAL_STATUS")
                .build();

        return transactionService.createTransaction(creationDto);
    }

    @Override
    public List<AccountShortcutDto> findAllAdmins() {
        return accountRepository.findAllByRoles_Name("ROLE_ADMIN").stream()
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<AccountShortcutDto> findAllAdminsByCity(String cityName) {
        return accountRepository.findAllByRoles_NameAndInstitution_City("ROLE_ADMIN", cityName).stream()
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public AccountFullDto findAdminById(long id) {
        Account account = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id)); //todo mb check of role

        return AccountDtoMapper.convertEntityToFullDto(account);
    }

    @Override
    @Transactional
    public AccountFullDto createAdmin(long institutionId) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        Account account = Account.builder()
                .firstName("admin")
                .lastName(institution.getName())
                .fatherName(institution.getCity())
                .phoneNumber("no phone") //todo check this
                .login(Generator.generateAdminLogin(institution.getName()))
                .password(Generator.generatePassword("ROLE_ADMIN"))
                .card(cardService.generateCard())
                .institution(institution)
                .build();

        accountRepository.save(account);

        institution.getAccounts().add(account);

        return AccountDtoMapper.convertEntityToFullDto(account);
    }

    @Override
    public void deleteAdminById(long id) {
        findAdminById(id);

        accountRepository.deleteById(id); //todo check of role mb
    }
}
