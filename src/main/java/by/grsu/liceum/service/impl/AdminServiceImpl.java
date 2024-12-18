package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.mapper.AdminDtoMapper;
import by.grsu.liceum.dto.mapper.ImageDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Image;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.entity.Role;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.FoundedAccountHasNoAdminPermitionsException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.InvalidRatingAmountException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.exception.RoleWithNameNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.ImageRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.repository.RoleRepository;
import by.grsu.liceum.service.AdminService;
import by.grsu.liceum.service.CardService;
import by.grsu.liceum.service.TransactionService;
import by.grsu.liceum.service.enums.RoleConstant;
import by.grsu.liceum.service.enums.TransactionStatusConstant;
import by.grsu.liceum.utils.Generator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableCaching
@PropertySource("classpath:business_settings.properties")
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final InstitutionRepository institutionRepository;
    private final RoleRepository roleRepository;
    private final CardService cardService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ImageRepository imageRepository;

    @Value("${property.admin.rating.min_value}")
    private int minRatingValue;
    @Value("${property.admin.rating.max_value}")
    private int maxRatingValue;

    @Override
    @Transactional
    public TransactionDto addRating(UUID institutionId, RatingDto ratingDto) {
        if(ratingDto.getValue() < this.minRatingValue || ratingDto.getValue() > this.maxRatingValue)
            throw new InvalidRatingAmountException(this.minRatingValue, this.maxRatingValue, ratingDto.getValue());

        Account account = Optional.ofNullable(accountRepository.findById(ratingDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ratingDto.getAccountId()));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        account.getCard().setBalance(account.getCard().getBalance() + ratingDto.getValue());

        TransactionCreationDto creationDto = TransactionCreationDto.builder()
                .cardId(account.getCard().getId())
                .balance(ratingDto.getValue())
                .status(TransactionStatusConstant.TRANSACTION_STATUS_ADMIN_ACCRUAL_STATUS.getValue())
                .build();

        return transactionService.createTransaction(institutionId, creationDto);
    }

    @Override
    @Transactional
    public TransactionDto getRating(UUID institutionId, RatingDto ratingDto) {
        if(ratingDto.getValue() < this.minRatingValue || ratingDto.getValue() > this.maxRatingValue)
            throw new InvalidRatingAmountException(this.minRatingValue, this.maxRatingValue, ratingDto.getValue());

        Account account = Optional.ofNullable(accountRepository.findById(ratingDto.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(ratingDto.getAccountId()));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        if(account.getCard().getBalance() - ratingDto.getValue() <= 0)
            throw new NotEnoughBalanceError(ratingDto.getAccountId(), account.getCard().getBalance());

        account.getCard().setBalance(account.getCard().getBalance() - ratingDto.getValue());

        TransactionCreationDto creationDto = TransactionCreationDto.builder()
                .cardId(account.getCard().getId())
                .balance(ratingDto.getValue())
                .status(TransactionStatusConstant.TRANSACTION_STATUS_ADMIN_ACCRUAL_STATUS.getValue())
                .build();

        return transactionService.createTransaction(institutionId, creationDto);
    }

    @Override
    public List<AdminShortcutDto> findAllAdmins() {
        return accountRepository.findAllByRoles_Name(RoleConstant.ROLE_ADMIN.getValue()).stream()
                .map(AdminDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<AdminShortcutDto> findAllAdminsByCity(String cityName) {
        return accountRepository.findAllByRoles_NameAndInstitution_City(RoleConstant.ROLE_ADMIN.getValue(), cityName).stream()
                .map(AdminDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public AdminFullDto findAdminById(UUID id) {
        Account account = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        if (account.getRoles().stream().noneMatch(x -> x.getName().equals(RoleConstant.ROLE_ADMIN.getValue())))
            throw new FoundedAccountHasNoAdminPermitionsException(account.getId(), account.getRoles());

        return AdminDtoMapper.convertEntityToFullDto(account);
    }

    @Override
    @Transactional
    public AdminFullDto createAdmin(UUID institutionId) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        Role role = Optional.ofNullable(roleRepository.findByName(RoleConstant.ROLE_ADMIN.getValue()))
                .orElseThrow(() -> new RoleWithNameNotFoundException(RoleConstant.ROLE_ADMIN.getValue()));

        String password = Generator.generatePassword(RoleConstant.ROLE_ADMIN.getValue());

        Account account = Account.builder() //todo adminCreationDto
                .firstName("admin")
                .lastName(institution.getName())
                .fatherName(institution.getCity())
                .phoneNumber("no phone") //todo check this
                .login(Generator.generateAdminLogin(institution.getName()))
                .password(bCryptPasswordEncoder.encode(password))
                .card(cardService.generateCard())
                .grade(52)
                .institution(institution)
                .roles(List.of(role))
                .build();

        accountRepository.save(account);

        role.getAccounts().add(account);
        institution.getAccounts().add(account);

        AdminFullDto dto = AdminDtoMapper.convertEntityToFullDto(account);
        dto.setPassword(password);

        return dto;
    }

    @Override
    @Transactional
    public AdminFullDto regeneratePassword(UUID institutionId, UUID adminId) {
        Account account = Optional.ofNullable(accountRepository.findById(adminId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(adminId));

        if(account.getRoles().stream().noneMatch(x -> x.getName().equals(RoleConstant.ROLE_ADMIN.getValue())))
            throw new InvalidPermissionsException();

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        String password = Generator.generatePassword(RoleConstant.ROLE_ADMIN.getValue());

        account.setPassword(bCryptPasswordEncoder.encode(password));

        AdminFullDto dto = AdminDtoMapper.convertEntityToFullDto(account);
        dto.setPassword(password);

        return dto;
    }

    @Override
    public void deleteAdminById(UUID id) {
        Account admin = Optional.ofNullable(accountRepository.findById(id))
                .orElseThrow(() -> new AccountWithIdNotFoundException(id));

        if (admin.getRoles().stream().noneMatch(x -> x.getName().equals(RoleConstant.ROLE_ADMIN.getValue())))
            throw new FoundedAccountHasNoAdminPermitionsException(admin.getId(), admin.getRoles());

        accountRepository.deleteById(id);
    }

    @Override
    @Transactional
    public AdminFullDto setImage(UUID institutionId, UUID accountId, ImageCreationDto creationDto) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        Image image = ImageDtoMapper.convertDtoToEntity(creationDto);
        imageRepository.save(image);

        account.setImage(image);

        return AdminDtoMapper.convertEntityToFullDto(account);
    }
}
