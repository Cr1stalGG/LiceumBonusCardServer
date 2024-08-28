package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/root/admins")
@RequiredArgsConstructor
public class SuperAdmin_AdminController {
    private final AdminService adminService;

    @GetMapping
    public List<AdminShortcutDto> findAllAdmins(){
        return adminService.findAllAdmins();
    }

    @GetMapping("/city/{city}")
    public List<AdminShortcutDto> findAllAdminsByCity(@PathVariable("city") String city){
        return adminService.findAllAdminsByCity(city);
    }

    @GetMapping("/{id}")
    public AdminFullDto findAdminById(@PathVariable("id") long id){
        return adminService.findAdminById(id);
    }

    @PostMapping("/{institutionId}")
    public AdminFullDto createAdmin(@PathVariable("institutionId") long institutionId){
        return adminService.createAdmin(institutionId);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") long id){
        adminService.deleteAdminById(id);
    }
}
