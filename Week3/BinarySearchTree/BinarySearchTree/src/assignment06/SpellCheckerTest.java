package assignment06;

import org.junit.jupiter.api.Test;

import java.io.File;

import static assignment06.SpellCheckerDemo.run_spell_check;
import static org.junit.jupiter.api.Assertions.*;

class SpellCheckerTest {

    @Test
    void addToDictionary() {
        SpellChecker spellChecker = new SpellChecker();
        spellChecker.addToDictionary("apple");
        spellChecker.addToDictionary("banana");
        spellChecker.addToDictionary("orange");
        assertTrue(spellChecker.dictionary.contains("apple"));
        assertTrue(spellChecker.dictionary.contains("banana"));
        assertTrue(spellChecker.dictionary.contains("orange"));
    }

    @Test
    void removeFromDictionary() {
        SpellChecker spellChecker = new SpellChecker();

        spellChecker.addToDictionary("apple");
        spellChecker.addToDictionary("banana");
        spellChecker.addToDictionary("orange");

        spellChecker.removeFromDictionary("apple");
        assertFalse(spellChecker.dictionary.contains("apple"));
        assertTrue(spellChecker.dictionary.contains("banana"));
        assertTrue(spellChecker.dictionary.contains("orange"));
    }

    @Test
    void spellCheck() {
        SpellChecker spellChecker = new SpellChecker();

        spellChecker.addToDictionary("hello");
        spellChecker.addToDictionary("are");
        spellChecker.addToDictionary("you");
        spellChecker.addToDictionary("a");
        spellChecker.addToDictionary("kidnapper");
        spellChecker.addToDictionary("strange");
        spellChecker.addToDictionary("man");
        spellChecker.addToDictionary("dressed");
        spellChecker.addToDictionary("in");
        spellChecker.addToDictionary("knitwear");

        assertTrue(spellChecker.dictionary.contains("kidnapper"));
        assertTrue(spellChecker.dictionary.contains("strange"));
        assertTrue(spellChecker.dictionary.contains("knitwear"));

        run_spell_check(spellChecker, "test.txt");
    }
}