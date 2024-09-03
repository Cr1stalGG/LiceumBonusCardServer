package by.grsu.liceum.service;

import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionUpdateDto;

import java.util.List;
import java.util.UUID;

public interface InstitutionService {
    List<InstitutionShortcutDto> findAllInstitutions();
    List<InstitutionShortcutDto> findAllInstitutionsByCity(String city);
    List<InstitutionShortcutDto> findAllInstitutionsByNameLike(String name);
    InstitutionFullDto findInstitutionById(UUID id);
    InstitutionFullDto createInstitution(InstitutionCreationDto creationDto);
    InstitutionFullDto updateInstitution(UUID id, InstitutionUpdateDto updateDto);
    InstitutionFullDto setImage(UUID id, ImageCreationDto imageCreationDto);
    void deleteInstitutionById(UUID id);
}
