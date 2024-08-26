package by.grsu.liceum.service;

import by.grsu.liceum.dto.account.AccountFullDto;
import by.grsu.liceum.dto.account.AccountShortcutDto;

import java.util.List;

public interface HeadTeacherService {
    List<AccountShortcutDto> findAllByGroup(long groupId);
    List<AccountShortcutDto> findAll();
    AccountFullDto findById(long id);

}
