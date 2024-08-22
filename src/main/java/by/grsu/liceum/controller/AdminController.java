package by.grsu.liceum.controller;

import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.dto.transaction.TransactionDto;
import by.grsu.liceum.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/push")
    @Secured(value = "ROLE_ADMIN")
    public TransactionDto addRating(@RequestBody RatingDto ratingDto){
        return adminService.addRating(ratingDto);
    }

    @PostMapping("/take")
    @Secured(value = "ROLE_ADMIN")
    public TransactionDto getRating(@RequestBody RatingDto ratingDto){
        return adminService.getRating(ratingDto);
    }
}
