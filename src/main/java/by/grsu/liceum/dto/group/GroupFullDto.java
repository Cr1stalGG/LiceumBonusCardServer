package by.grsu.liceum.dto.group;

import by.grsu.liceum.dto.account.AccountShortcutDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GroupFullDto {
    private long uuid;
    private String name;
    private AccountShortcutDto admin;
    private List<AccountShortcutDto> members;
}
