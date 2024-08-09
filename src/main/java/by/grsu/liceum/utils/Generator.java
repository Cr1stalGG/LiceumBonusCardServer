package by.grsu.liceum.utils;

import by.grsu.liceum.dto.utils.GeneratorLoginDto;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Generator {
    private static final int LENGTH = 8;

    public static String generateLogin(GeneratorLoginDto loginDto){
        StringBuilder login  = new StringBuilder();

        login.append(Transliterator.transliterateWord(loginDto.getLastName().trim()))
                .append("_");
        login.append(Transliterator.transliterateWord(String.valueOf(loginDto.getFirstName().charAt(0))))
                .append(Transliterator.transliterateWord(String.valueOf(loginDto.getLastName().charAt(0))))
                .append("_");
        login.append(String.format("%02d", loginDto.getYearOfStartOfStudies() % 100));

        return login.toString().toLowerCase();
    }

    public static String generatePassword() {
        final String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder password = new StringBuilder();

        for(byte i = 0; i < LENGTH; ++i)
            password.append(dict.charAt(new Random().nextInt(dict.length())));

        return String.valueOf(password);
    }
}
