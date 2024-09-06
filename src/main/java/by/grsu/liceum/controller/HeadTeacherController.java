package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.group.GroupShortcutDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.HeadTeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/head")
@RequiredArgsConstructor
public class HeadTeacherController {
    private final HeadTeacherService headTeacherService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public List<GroupShortcutDto> findGroupsOfInstitution(@PathVariable("institutionId") UUID institutionId){
        return headTeacherService.findAll(institutionId);
    }

    @PostMapping("/push")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public TransactionDto addRating(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid RatingDto ratingDto){
        return headTeacherService.addRating(institutionId, ratingDto);
    }

    @PostMapping("/take")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_HEAD_TEACHER')")
    public TransactionDto getRating(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid RatingDto ratingDto){
        return headTeacherService.getRating(institutionId, ratingDto);
    }
}
