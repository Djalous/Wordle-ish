import main.Word;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test ensures only
 * 5 letter guesses are stored
 * in the app and backspace function
 */
public class LetterGuessTests {

    @Test
    public void testPushChar_ValidLength() {
        Word word = new Word(5);
        for (char c = 'a'; c <= 'e'; c++ ) {
            word.pushChar(c);
        }

        assertEquals("abcde", word.toString());
    }
    @Test
    public void testPushChar_ExceedLength() {
        Word word = new Word(5);
        for(char c = 'a'; c <= 'f'; c++) {
            word.pushChar(c);
        }
        assertEquals("abcde", word.toString());
        assertFalse(word.pushChar('g'));
    }
    @Test
    public void testBackspace_Empty() {
        Word word  = new Word(5);
        word.backspace();
        assertEquals("", word.toString());
    }
    @Test
    public void testBackspace_NotEmpty() {
        Word word = new Word(5);
        word.pushChar('a');
        word.pushChar('b');

        word.backspace();
        assertEquals("a",word.toString());
    }

    /**
     * NonAlpha Test
     */
    @Test
    public void testPushChar_NonAlpha() {
        Word word = new Word(5);
        assertFalse(word.pushChar('1'));
        assertFalse(word.pushChar('!'));
        assertFalse(word.pushChar('&'));
        assertTrue(word.pushChar('a'));
    }

    /**
     * Case Sensitive Test
     */
    @Test
    public void testPushChar_CaseSensitivity() {
        Word word = new Word(5);
        word.pushChar('A');
        word.pushChar('b');
        word.pushChar('C');

        assertEquals("abc",word.toString()); //should convert to lowercase

        word.backspace(); //remove 'C'
        word.pushChar('c');

        assertEquals("abc", word.toString()); //match lowercase 'c'
    }
}

