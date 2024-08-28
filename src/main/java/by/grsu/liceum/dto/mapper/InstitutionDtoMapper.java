package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.activity_type.ActivityTypeShortcutDto;
import by.grsu.liceum.dto.bonus.BonusShortcutDto;
import by.grsu.liceum.dto.institution.InstitutionFullDto;
import by.grsu.liceum.dto.institution.InstitutionShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.ActivityType;
import by.grsu.liceum.entity.Bonus;
import by.grsu.liceum.entity.Institution;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@UtilityClass
public class InstitutionDtoMapper {

    public static InstitutionShortcutDto convertEntityToShortcutDto(Institution source) {
        return Optional.ofNullable(source)
                .map(InstitutionDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static InstitutionFullDto convertEntityToFullDto(Institution source) {
        return Optional.ofNullable(source)
                .map(InstitutionDtoMapper::buildFullDto)
                .orElse(null);
    }

    private static InstitutionFullDto buildFullDto(Institution source) {
        return InstitutionFullDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .city(source.getCity())
                .accounts(buildAccounts(source.getAccounts()))
                .activityTypes(buildActivityTypes(source.getActivityTypes()))
                .bonuses(buildBonuses(source.getBonuses()))
                .build();
    }

    private static List<BonusShortcutDto> buildBonuses(List<Bonus> source) {
        if (source == null)
            return new ArrayList<>();
        else
            return source.stream()
                .map(BonusDtoMapper::convertEntityToShortcutDto)
                .toList();
    }

    private static List<ActivityTypeShortcutDto> buildActivityTypes(List<ActivityType> source) {
        if(source == null)
            return new ArrayList<>();
        else
            return source.stream()
                    .map(ActivityTypeDtoMapper::convertEntityToShortCutDto)
                    .toList();
    }

    private static List<AccountShortcutDto> buildAccounts(List<Account> source) {
        if(source == null)
            return new ArrayList<>();
        else
            return source.stream()
                    .map(AccountDtoMapper::convertEntityToShortcutDto)
                    .toList();
    }

    private static InstitutionShortcutDto buildShortcutDto(Institution source) {
        return InstitutionShortcutDto.builder()
                .uuid(source.getId())
                .name(source.getName())
                .city(source.getCity())
                .build();
    }
}
