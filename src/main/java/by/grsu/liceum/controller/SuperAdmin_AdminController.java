package by.grsu.liceum.controller;

import by.grsu.liceum.dto.account.admin.AdminFullDto;
import by.grsu.liceum.dto.account.admin.AdminShortcutDto;
import by.grsu.liceum.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/root/admins")
@RequiredArgsConstructor
public class SuperAdmin_AdminController {
    private final AdminService adminService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<AdminShortcutDto> findAllAdmins(){
        return adminService.findAllAdmins();
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<AdminShortcutDto> findAllAdminsByCity(@PathVariable("city") String city){
        return adminService.findAllAdminsByCity(city);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public AdminFullDto findAdminById(@PathVariable("id") long id){
        return adminService.findAdminById(id);
    }

    @PostMapping("/{institutionId}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public AdminFullDto createAdmin(@PathVariable("institutionId") long institutionId){
        return adminService.createAdmin(institutionId);
    }

    @PutMapping("/{institutionId}/regenerate/password/{adminId}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public AdminFullDto regeneratePassword(@PathVariable("institutionId") long institutionId, @PathVariable("adminId") long adminId){
        return adminService.regeneratePassword(institutionId, adminId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void deleteById(@PathVariable("id") long id){
        adminService.deleteAdminById(id);
    }
}
