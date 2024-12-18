package by.grsu.liceum.dto.account;

import by.grsu.liceum.dto.image.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountShortcutDto implements Serializable {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String fatherName;
    private int grade;
    private String phoneNumber;//todo optional<?
    private ImageDto image;
}
