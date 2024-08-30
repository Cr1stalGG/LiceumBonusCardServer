package by.grsu.liceum.controller;

import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionUpdateDto;
import by.grsu.liceum.service.InstitutionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/root/institutions")
@RequiredArgsConstructor
public class SuperAdmin_InstitutionController {
    private final InstitutionService institutionService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<InstitutionShortcutDto> findAllInstitutions(){
        return institutionService.findAllInstitutions();
    }

    @GetMapping("/city/{city}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<InstitutionShortcutDto> findAllInstitutionsByCity(@PathVariable("city") String city){
        return institutionService.findAllInstitutionsByCity(city);
    }

    @GetMapping("/name/{name}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public List<InstitutionShortcutDto> findAllInstitutionsByNameLike(@PathVariable("name") String name){
        return institutionService.findAllInstitutionsByNameLike(name);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public InstitutionFullDto findInstitutionById(@PathVariable("id") UUID id){
        return institutionService.findInstitutionById(id);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public InstitutionFullDto createInstitution(@RequestBody InstitutionCreationDto creationDto){
        return institutionService.createInstitution(creationDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public InstitutionFullDto updateInstitution(@PathVariable("id") UUID id, @RequestBody InstitutionUpdateDto updateDto){
        return institutionService.updateInstitution(id, updateDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
    public void deleteInstitutionById(@PathVariable("id") UUID id){
        institutionService.deleteInstitutionById(id);
    }
}
