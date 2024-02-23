import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LetterGuessTests {

    @Test
    public void testPushChar_ValidLength(){
        Word word = new Word(5);
        for (char c = 'a'; c <= 'e'; c++ ) {
            word.pushChar(c);
        }

        assertEquals("abcde", word.toString());
    }


}

