package by.grsu.liceum.dto.group;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupFullDto {
    private UUID uuid;
    private String name;
    private AccountShortcutDto admin;
    private List<AccountShortcutDto> members;
}
