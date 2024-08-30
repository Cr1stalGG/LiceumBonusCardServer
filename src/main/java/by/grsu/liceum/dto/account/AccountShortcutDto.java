package by.grsu.liceum.dto.account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountShortcutDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private String phoneNumber;//todo optional<?
}
