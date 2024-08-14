package by.grsu.liceum.controller;

import by.grsu.liceum.dto.admin.RatingDto;
import by.grsu.liceum.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admins")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/add")
    public void addRating(@RequestBody RatingDto ratingDto){
        adminService.addRating(ratingDto);
    }

    @PostMapping("/get")
    public void getRating(@RequestBody RatingDto ratingDto){
        adminService.getRating(ratingDto);
    }
}
