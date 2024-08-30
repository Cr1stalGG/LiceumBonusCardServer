package by.grsu.liceum.service;

import by.grsu.liceum.dto.solved_activity.SolveActivityRequest;
import by.grsu.liceum.dto.solved_activity.SolvedActivityFullDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;

import java.util.List;
import java.util.UUID;

public interface SolvedActivityService {
    List<SolvedActivityShortcutDto> findAllByInstitutionId(UUID institutionId);
    List<SolvedActivityShortcutDto> findAllByAccountId(UUID institutionId, UUID accountId);
    List<SolvedActivityShortcutDto> findAllByActivityId(UUID institutionId, UUID activityId);
    SolvedActivityFullDto findById(UUID institutionId, UUID id);
    SolvedActivityFullDto solveActivity(UUID institutionId, SolveActivityRequest request);
    void deleteById(UUID institutionId, UUID id);
}
