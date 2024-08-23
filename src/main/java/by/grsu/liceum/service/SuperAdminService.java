package by.grsu.liceum.service;

import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;

import java.util.List;

public interface SuperAdminService {
    List<InstitutionShortcutDto> findAll();
    List<InstitutionShortcutDto> findAllByCity(String city);
    List<InstitutionShortcutDto> findAllByNameLike(String name);
    InstitutionFullDto findById(long id);
    InstitutionFullDto createInstitution(InstitutionCreationDto creationDto);
    void deleteById(long id);
}
