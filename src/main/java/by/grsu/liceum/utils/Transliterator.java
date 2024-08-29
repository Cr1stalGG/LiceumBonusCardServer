package by.grsu.liceum.utils;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class Transliterator {
    private static final Set<Character> vowels = Set.of('а', 'у', 'о', 'э', 'и', 'ы', 'я', 'ю', 'ё', 'е');

    public static String transliterateWordToAbbreviation(String name){
        StringBuilder abbreviation = new StringBuilder();

        for(String word : name.trim().split("[ .№]")) {
            for (int i = 0; i < word.length(); ++i) {
                if(i == 0 && vowels.contains(word.toLowerCase().charAt(i))){
                    abbreviation.append(word.toLowerCase().charAt(i));
                    break;
                }
                else if(i == 0 || !vowels.contains(word.toLowerCase().charAt(i)))
                    abbreviation.append(word.toLowerCase().charAt(i));
                else
                    break;
            }
        }

        return Transliterator.transliterateWord(abbreviation.toString());
    }

    public static String transliterateWord(String word){
        StringBuilder transliteratedWord = new StringBuilder();

        for(Character c : word.toUpperCase().toCharArray())
            transliteratedWord.append(cyr2lat(c));

        return transliteratedWord.toString();
    }

    private static String cyr2lat(char ch){
        return switch (ch) {
            case 'А' -> "A";
            case 'Б' -> "B";
            case 'В' -> "V";
            case 'Г' -> "G";
            case 'Д' -> "D";
            case 'Е' -> "E";
            case 'Ё' -> "JE";
            case 'Ж' -> "ZH";
            case 'З' -> "Z";
            case 'И' -> "I";
            case 'Й' -> "Y";
            case 'К' -> "K";
            case 'Л' -> "L";
            case 'М' -> "M";
            case 'Н' -> "N";
            case 'О' -> "O";
            case 'П' -> "P";
            case 'Р' -> "R";
            case 'С' -> "S";
            case 'Т' -> "T";
            case 'У' -> "U";
            case 'Ф' -> "F";
            case 'Х' -> "KH";
            case 'Ц' -> "C";
            case 'Ч' -> "CH";
            case 'Ш' -> "SH";
            case 'Щ' -> "JSH";
            case 'Ъ' -> "HH";
            case 'Ы' -> "IH";
            case 'Ь' -> "JH";
            case 'Э' -> "EH";
            case 'Ю' -> "JU";
            case 'Я' -> "JA";
            default -> String.valueOf(ch);
        };
    }
}
