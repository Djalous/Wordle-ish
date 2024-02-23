import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

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

}

