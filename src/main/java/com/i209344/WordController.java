package com.i209344;

import org.springframework.web.bind.annotation.*;

import java.util.function.IntPredicate;

@RestController
@RequestMapping("/api/string")
public class WordController {

    @GetMapping("/uppercase-characters-count/{word}")
    @ResponseBody
    public long getUpperCaseLetters(@PathVariable String word) {
        return getInfoAboutWordWithPredicate(word, Character::isUpperCase);
    }

    @GetMapping("/lowercase-characters-count/{word}")
    @ResponseBody
    public long getLowerCaseLetters(@PathVariable String word) {
        return getInfoAboutWordWithPredicate(word, Character::isLowerCase);
    }

    @GetMapping("/digits-count/{word}")
    @ResponseBody
    public long getDigits(@PathVariable String word) {
        return getInfoAboutWordWithPredicate(word, Character::isDigit);
    }

    @GetMapping("/special-characters-count/{word}")
    @ResponseBody
    public long getSpecialChars(@PathVariable String word) {
        return word.chars()
                .filter(this::isSpecial)
                .count();
    }

    @GetMapping("/information/{word}")
    @ResponseBody
    public String getInfoAboutWord(@PathVariable String word) {
        long digits = 0L, lowercaseLetters = 0L, uppercaseLetters = 0L, specials = 0L;

        digits = getInfoAboutWordWithPredicate(word, Character::isDigit);
        lowercaseLetters = getInfoAboutWordWithPredicate(word, Character::isLowerCase);
        uppercaseLetters = getInfoAboutWordWithPredicate(word, Character::isUpperCase);
        specials = getInfoAboutWordWithPredicate(word, this::isSpecial);

        return "Digits count: " + digits + ", lowercase letters count: " + lowercaseLetters +
                ", uppercase letters count: " + uppercaseLetters + ", special signs count: " + specials;
    }

    private boolean isSpecial(int codePoint) {
        return (codePoint < 'A' || codePoint > 'Z') && (codePoint < 'a' || codePoint > 'z') && (codePoint < '0' || codePoint > '9');
    }

    private long getInfoAboutWordWithPredicate(String word, IntPredicate intPredicate) {
        return word.chars()
                .filter(intPredicate)
                .count();
    }

}
