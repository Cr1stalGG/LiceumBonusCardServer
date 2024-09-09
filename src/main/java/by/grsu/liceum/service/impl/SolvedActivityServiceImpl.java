package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.mapper.SolvedActivityDtoMapper;
import by.grsu.liceum.dto.solved_activity.SolveActivityRequest;
import by.grsu.liceum.dto.solved_activity.SolvedActivityFullDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Activity;
import by.grsu.liceum.entity.SolvedActivity;
import by.grsu.liceum.exception.AccountWithIdNotFoundException;
import by.grsu.liceum.exception.ActivityWithIdNotFoundException;
import by.grsu.liceum.exception.CountOfMembersIsExpiredException;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.exception.InvalidActivityCodeException;
import by.grsu.liceum.exception.InvalidPermissionsException;
import by.grsu.liceum.exception.SolvedActivityWithIdNotFoundException;
import by.grsu.liceum.repository.AccountRepository;
import by.grsu.liceum.repository.ActivityRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.repository.SolvedActivityRepository;
import by.grsu.liceum.service.SolvedActivityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolvedActivityServiceImpl implements SolvedActivityService {
    private final SolvedActivityRepository solvedActivityRepository;
    private final AccountRepository accountRepository;
    private final ActivityRepository activityRepository;
    private final InstitutionRepository institutionRepository;

    @Override
    public List<SolvedActivityShortcutDto> findAllByInstitutionId(UUID institutionId) {
        Optional.ofNullable(institutionRepository.findById(institutionId))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(institutionId));

        return solvedActivityRepository.findAllByActivity_ActivityType_Institution_Id(institutionId).stream()
                .map(SolvedActivityDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<SolvedActivityShortcutDto> findAllByAccountId(UUID institutionId, UUID accountId) {
        Account account = Optional.ofNullable(accountRepository.findById(accountId))
                .orElseThrow(() -> new AccountWithIdNotFoundException(accountId));

        if(!account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return solvedActivityRepository.findAllByAccount_Id(accountId).stream()
                .map(SolvedActivityDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<SolvedActivityShortcutDto> findAllByActivityId(UUID institutionId, UUID activityId) {
        Activity activity = Optional.ofNullable(activityRepository.findById(activityId))
                .orElseThrow(() -> new ActivityWithIdNotFoundException(activityId));

        if(!activity.getActivityType().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return solvedActivityRepository.findAllByActivity_Id(activityId).stream()
                .map(SolvedActivityDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    @SneakyThrows
    public SolvedActivityFullDto findById(UUID institutionId, UUID id) {
        SolvedActivity solvedActivity = Optional.ofNullable(solvedActivityRepository.findById(id))
                .orElseThrow(() -> new SolvedActivityWithIdNotFoundException(id));

        if(!solvedActivity.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        return SolvedActivityDtoMapper.convertEntityToFullDto(solvedActivity);
    }

    @Override
    @Transactional
    public SolvedActivityFullDto solveActivity(UUID institutionId, SolveActivityRequest request) {
        Activity activity = Optional.ofNullable(activityRepository.findById(request.getActivityId()))
                .orElseThrow(() -> new ActivityWithIdNotFoundException(request.getActivityId()));

        Account account = Optional.ofNullable(accountRepository.findById(request.getAccountId()))
                .orElseThrow(() -> new AccountWithIdNotFoundException(request.getAccountId()));

        if(!activity.getActivityType().getInstitution().getId().equals(institutionId) || !account.getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        if(!activity.getCode().equals(request.getCode()))
            throw new InvalidActivityCodeException(activity.getCode(), request.getCode());

        account.getCard().setBalance(account.getCard().getBalance() + activity.getActivityType().getCost());

        if(activity.getCountOfMembers() > 0)
            activity.setCountOfMembers(activity.getCountOfMembers() - 1);
        else
            throw new CountOfMembersIsExpiredException();

        SolvedActivity solvedActivity = SolvedActivity.builder()
                .account(account)
                .activity(activity)
                .timeOfSolving(new Date(System.currentTimeMillis()))
                .build();
        //todo create transaction
        solvedActivityRepository.save(solvedActivity);

        account.getSolvedActivities().add(solvedActivity);
        activity.getSolvedActivities().add(solvedActivity);

        return SolvedActivityDtoMapper.convertEntityToFullDto(solvedActivity);
    }

    @Override
    @Transactional
    public void deleteById(UUID institutionId, UUID id) {
        SolvedActivity solvedActivity = Optional.ofNullable(solvedActivityRepository.findById(id))
                .orElseThrow(() -> new SolvedActivityWithIdNotFoundException(id));

        if(!solvedActivity.getAccount().getInstitution().getId().equals(institutionId))
            throw new InvalidPermissionsException();

        solvedActivityRepository.deleteById(id);
    }
}
