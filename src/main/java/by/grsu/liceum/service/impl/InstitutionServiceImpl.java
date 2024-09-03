package by.grsu.liceum.service.impl;

import by.grsu.liceum.dto.image.ImageCreationDto;
import by.grsu.liceum.dto.institution.InstitutionCreationDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionUpdateDto;
import by.grsu.liceum.dto.mapper.ImageDtoMapper;
import by.grsu.liceum.dto.mapper.InstitutionDtoMapper;
import by.grsu.liceum.entity.Image;
import by.grsu.liceum.entity.Institution;
import by.grsu.liceum.exception.InstitutionWithIdNotFoundException;
import by.grsu.liceum.repository.ImageRepository;
import by.grsu.liceum.repository.InstitutionRepository;
import by.grsu.liceum.service.InstitutionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InstitutionServiceImpl implements InstitutionService {
    private final InstitutionRepository institutionRepository;
    private final ImageRepository imageRepository;

    @Override
    public List<InstitutionShortcutDto> findAllInstitutions() {
        return institutionRepository.findAll().stream()
                .map(InstitutionDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<InstitutionShortcutDto> findAllInstitutionsByCity(String city) {
        return institutionRepository.findAllByCity(city).stream()
                .map(InstitutionDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public List<InstitutionShortcutDto> findAllInstitutionsByNameLike(String name) {
        return institutionRepository.findAllByNameLike(name).stream()
                .map(InstitutionDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    @Override
    public InstitutionFullDto findInstitutionById(UUID id) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(id))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(id));

        return InstitutionDtoMapper.convertEntityToFullDto(institution);
    }

    @Override
    @Transactional
    public InstitutionFullDto createInstitution(InstitutionCreationDto creationDto) {
        Institution institution = Institution.builder()
                .name(creationDto.getName())
                .city(creationDto.getCity())
                .accounts(new ArrayList<>())
                .build();

        institutionRepository.save(institution);

        return InstitutionDtoMapper.convertEntityToFullDto(institution);
    }

    @Override
    public InstitutionFullDto updateInstitution(UUID id, InstitutionUpdateDto updateDto) {
        return null; //todo
    }

    @Override
    @Transactional
    public InstitutionFullDto setImage(UUID id, ImageCreationDto imageCreationDto) {
        Institution institution = Optional.ofNullable(institutionRepository.findById(id))
                .orElseThrow(() -> new InstitutionWithIdNotFoundException(id));

        Image image = ImageDtoMapper.convertDtoToEntity(imageCreationDto);
        imageRepository.save(image);

        institution.setImage(image);

        return InstitutionDtoMapper.convertEntityToFullDto(institution);
    }

    @Override
    public void deleteInstitutionById(UUID id) {
        findInstitutionById(id);

        institutionRepository.deleteById(id);
    }

}
