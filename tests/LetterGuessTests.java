import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This test ensures only
 * 5 letter guesses are stored
 * in the app and backspace function
 */
public class LetterGuessTests {

    @Test
    public void testPushChar_ValidLength(){
        Word word = new Word(5);
        for (char c = 'a'; c <= 'e'; c++ ) {
            word.pushChar(c);
        }

        assertEquals("abcde", word.toString());
    }
    @Test
    public void testPushChar_ExceedLength(){
        Word word = new Word(5);
        for(char c = 'a'; c <= 'f'; c++) {
            word.pushChar(c);
        }
        assertEquals("abcde", word.toString());
        assertFalse(word.pushChar('g'));
    }
    @Test
    public void testBackspace_Empty(){
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
@Test
    public void testPushChar_NonAlpha(){
        Word word = new Word(5);
        assertFalse(word.pushChar('1'));
        assertFalse(word.pushChar('!'));
        assertFalse(word.pushChar('&'));
        assertTrue(word.pushChar('a'));
}
}

