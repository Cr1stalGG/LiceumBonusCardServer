package by.grsu.liceum.service;

import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionUpdateDto;

import java.util.List;

public interface InstitutionService {
    List<InstitutionShortcutDto> findAllInstitutions();
    List<InstitutionShortcutDto> findAllInstitutionsByCity(String city);
    List<InstitutionShortcutDto> findAllInstitutionsByNameLike(String name);
    InstitutionFullDto findInstitutionById(long id);
    InstitutionFullDto createInstitution(InstitutionCreationDto creationDto);
    InstitutionFullDto updateInstitution(long id, InstitutionUpdateDto updateDto);
    void deleteInstitutionById(long id);
}
