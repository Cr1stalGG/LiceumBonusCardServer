package by.grsu.liceum.service;

import by.grsu.liceum.dto.solved_activity.SolveActivityRequest;
import by.grsu.liceum.dto.solved_activity.SolvedActivityFullDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;

import java.util.List;

public interface SolvedActivityService {
    List<SolvedActivityShortcutDto> findAllByInstitutionId(long institutionId);
    List<SolvedActivityShortcutDto> findAllByAccountId(long institutionId, long accountId);
    List<SolvedActivityShortcutDto> findAllByActivityId(long institutionId, long activityId);
    SolvedActivityFullDto findById(long institutionId, long id);
    SolvedActivityFullDto solveActivity(long institutionId, SolveActivityRequest request);
    void deleteById(long institutionId, long id);
}
