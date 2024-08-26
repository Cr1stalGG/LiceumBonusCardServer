package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.group.GroupFullDto;
import by.grsu.liceum.dto.mapper.GroupDtoMapper;
import by.grsu.liceum.dto.transaction.TransactionCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Group;
import by.grsu.liceum.entity.enums.StatusConstant;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.GroupWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidRatingAmountException;
import by.grsu.liceum.exception.NotEnoughBalanceError;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.GroupRepository;
import by.grsu.liceum.service.HeadTeacherService;
import by.grsu.liceum.service.TransactionService;
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
public class HeadTeacherServiceImpl implements HeadTeacherService {
    private final AccountRepository accountRepository;
    private final TransactionService transactionService;
    private final GroupRepository groupRepository;

    @Value("${property.head_teacher.rating.min_value}")
    private int minRatingValue;
    @Value("${property.head_teacher.rating.max_value}")
    private int maxRatingValue;

    @Override
    public GroupFullDto findGroupById(long groupId) {
        Group group = Optional.ofNullable(groupRepository.findById(groupId))
                .orElseThrow(() -> new GroupWithIdNotFoundException(groupId));

        return GroupDtoMapper.convertEntityToFullDto();
    }

    @Override
    public List<AccountShortcutDto> findAll(long headTeacherId) {
        return List.of();
    }

    @Override
    public AccountFullDto findById(long id) {
        return null;
    }

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
                .status(StatusConstant.ADMIN_ACCRUAL_STATUS)
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
                .status(StatusConstant.ADMIN_ACCRUAL_STATUS)
                .build();

        return transactionService.createTransaction(creationDto);
    }
}
