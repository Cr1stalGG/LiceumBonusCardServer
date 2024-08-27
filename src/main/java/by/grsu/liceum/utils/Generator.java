package by.grsu.liceum.utils;

import by.grsu.liceum.dto.utils.GeneratorLoginDto;
import by.grsu.liceum.exception.InvalidRoleNameException;
import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class Generator {
    private static final int ACCOUNT_PASSWORD_LENGTH = 8;
    private static final int ADMIN_PASSWORD_LENGTH = 12;

    private static final Random random = new Random(System.currentTimeMillis());

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

    public static String generatePassword(String role) {
        final String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

        StringBuilder password = new StringBuilder();

        int length = switch (role){
            case "ROLE_USER" -> ACCOUNT_PASSWORD_LENGTH;
            case "ROLE_ADMIN" -> ADMIN_PASSWORD_LENGTH;
            default -> throw new InvalidRoleNameException(role);
        };

        for(byte i = 0; i < length; ++i)
            password.append(dict.charAt(random.nextInt(dict.length())));

        return String.valueOf(password);
    }

    public static String generateCardNumber(){
        StringBuilder cardNumber = new StringBuilder();

        for(int i = 0; i < 4; ++i)
            cardNumber.append(String.format("%04d", random.nextInt( 0,9999)));

        return cardNumber.toString();
    }

    public static String generateBonusToken(){
        final String dict = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder token = new StringBuilder();

        for(byte i = 0; i < ACCOUNT_PASSWORD_LENGTH; ++i)
            token.append(dict.charAt(random.nextInt(dict.length())));

        return String.valueOf(token);
    }

    public static String generateAdminLogin(String institutionName){
        StringBuilder login = new StringBuilder("admin");

        login.append("_").append(Transliterator.transliterateWordToAbbreviation(institutionName));
        login.append("_").append(String.format("%4d", random.nextInt(0, 9999)));

        return login.toString();
    }
}
