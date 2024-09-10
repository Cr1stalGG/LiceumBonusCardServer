package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.RatingDto;
import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.AccountService;
import by.grsu.liceum.service.AdminService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.simpleframework.xml.Path;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/institutions/{institutionId}/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/push")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public TransactionDto addRating(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid RatingDto ratingDto){
        return adminService.addRating(institutionId, ratingDto);
    }

    @PostMapping("/take")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public TransactionDto getRating(@PathVariable("institutionId") UUID institutionId, @RequestBody @Valid RatingDto ratingDto){
        return adminService.getRating(institutionId, ratingDto);
    }

    @PutMapping("/{adminId}/images")
    @CacheEvict("admins")
    @PreAuthorize("hasAnyAuthority('ROLE_SUPER_ADMIN', 'ROLE_ADMIN')")
    public AdminFullDto setImage(@PathVariable("institutionId") UUID institutionId, @PathVariable("adminId") UUID adminId, @RequestBody @Valid ImageCreationDto creationDto){
        return adminService.setImage(institutionId, adminId, creationDto);
    }
}
