package by.grsu.liceum.controller;

import by.grsu.liceum.dto.solved_activity.SolveActivityRequest;
import by.grsu.liceum.dto.solved_activity.SolvedActivityFullDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;
import by.grsu.liceum.service.SolvedActivityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/activities/solved")
@RequiredArgsConstructor
public class SolvedActivityController {
    private final SolvedActivityService solvedActivityService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_SALE_UNIT')")
    public List<SolvedActivityShortcutDto> findAllByInstitutionId(@PathVariable("institutionId") UUID institutionId) {
        return solvedActivityService.findAllByInstitutionId(institutionId);
    }

    @GetMapping("/accounts/{accountId}")
    @PreAuthorize("isAuthenticated()")
    public List<SolvedActivityShortcutDto> findAllByAccountId(@PathVariable("institutionId") UUID institutionId, @PathVariable("accountId") UUID accountId) {
        return solvedActivityService.findAllByAccountId(institutionId, accountId);
    }

    @GetMapping("/activities/{activityId}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER', 'ROLE_SALE_UNIT')")
    public List<SolvedActivityShortcutDto> findAllByActivityId(@PathVariable("institutionId") UUID institutionId, @PathVariable("activityId") UUID activityId) {
        return solvedActivityService.findAllByActivityId(institutionId, activityId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("isAuthenticated()")
    public SolvedActivityFullDto findById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        return solvedActivityService.findById(institutionId, id);
    }

    @PostMapping
    @PreAuthorize("isAuthenticated()")
    public SolvedActivityFullDto solveActivity(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid SolveActivityRequest request){
        return solvedActivityService.solveActivity(institutionId, request);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public void deleteById(@PathVariable("institutionId") UUID institutionId, @PathVariable("id") UUID id){
        solvedActivityService.deleteById(institutionId, id);
    }
}