package by.grsu.liceum.dto.mapper;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import by.grsu.liceum.dto.activity.ActivityShortcutDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityFullDto;
import by.grsu.liceum.dto.solved_activity.SolvedActivityShortcutDto;
import by.grsu.liceum.entity.Account;
import by.grsu.liceum.entity.Activity;
import by.grsu.liceum.entity.SolvedActivity;
import lombok.experimental.UtilityClass;

import java.util.Optional;

@UtilityClass
public class SolvedActivityDtoMapper {

    public static SolvedActivityShortcutDto convertEntityToShortcutDto(SolvedActivity source) {
        return Optional.ofNullable(source)
                .map(SolvedActivityDtoMapper::buildShortcutDto)
                .orElse(null);
    }

    public static SolvedActivityFullDto convertEntityToFullDto(SolvedActivity source) {
        return Optional.ofNullable(source)
                .map(SolvedActivityDtoMapper::buildFullDto)
                .orElse(null);
    }

    private static SolvedActivityFullDto buildFullDto(SolvedActivity source) {
        return SolvedActivityFullDto.builder()
                .uuid(source.getId())
                .account(buildAccount(source.getAccount()))
                .activity(buildActivity(source.getActivity()))
                .code(source.getCode())
                .timeOfSolving(source.getTimeOfSolving())
                .build();
    }

    private static ActivityShortcutDto buildActivity(Activity source) {
        return Optional.ofNullable(source)
                .map(ActivityDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static AccountShortcutDto buildAccount(Account source) {
        return Optional.ofNullable(source)
                .map(AccountDtoMapper::convertEntityToShortcutDto)
                .orElse(null);
    }

    private static SolvedActivityShortcutDto buildShortcutDto(SolvedActivity source) {
        return SolvedActivityShortcutDto.builder()
                .uuid(source.getId())
                .activityName(source.getActivity().getName())
                .rating(source.getActivity().getActivityType().getCost())
                .timeOfSolving(source.getTimeOfSolving())
                .build();
    }
}
