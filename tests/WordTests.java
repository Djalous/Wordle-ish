import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTests {

    @Test
    public void testPush() {
        Word w1 = new Word(5);

        w1.pushChar('c');
        w1.pushChar('o');
        w1.pushChar('o');
        w1.pushChar('l');

        Assertions.assertEquals(w1.toString(), "cool");

        w1.backspace();

        Assertions.assertEquals(w1.toString(), "coo");

        w1.pushChar('l');
        w1.pushChar('l');

        Assertions.assertEquals(w1.toString(), "cooll");
        Assertions.assertFalse(w1.pushChar('l'));
        Assertions.assertEquals(w1.toString(), "cooll");
    }

    @Test
    public void testEquals() {
        Word w1 = new Word(5);
        Word w2 = new Word(5);

        w1.pushChar('c');
        w1.pushChar('o');
        w1.pushChar('o');
        w1.pushChar('l');

        w2.pushChar('c');
        w2.pushChar('o');
        w2.pushChar('o');
        w2.pushChar('l');

        Assertions.assertEquals(w1, w2);

        w2.backspace();

        Assertions.assertNotEquals(w1, w2);

        Word w3 = new Word(6);

        w3.pushChar('c');
        w3.pushChar('o');
        w3.pushChar('o');
        w3.pushChar('l');

        Assertions.assertNotEquals(w1, w3);
        Assertions.assertEquals(w1.toString(), w3.toString());
    }

    @Test
    public void testValidityData() {
        Word w1 = new Word(5);

        w1.pushChar('c');
        w1.pushChar('o');
        w1.pushChar('o');
        w1.pushChar('l');
        w1.pushChar('l');

        CharValidity[] data = w1.getCorrect(new Word("cooll"));
        CharValidity[] test = new CharValidity[] {
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
        };

        Assertions.assertArrayEquals(test, data);

        CharValidity[] test2 = new CharValidity[] {
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
                CharValidity.PRESENT_CHAR,
                CharValidity.PRESENT_CHAR,
                CharValidity.CORRECT_POSITION,
        };

        CharValidity[] data2 = w1.getCorrect(new Word("colol"));

        Assertions.assertArrayEquals(test2, data2);

        CharValidity[] test3 = new CharValidity[] {
                CharValidity.PRESENT_CHAR,
                CharValidity.CORRECT_POSITION,
                CharValidity.PRESENT_CHAR,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
        };

        CharValidity[] data3 = w1.getCorrect(new Word("aodcf"));

        Assertions.assertArrayEquals(test3, data3);

        CharValidity[] data4 = w1.getCorrect(new Word("12345"));

        CharValidity[] test4 = new CharValidity[] {
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
        };

        Assertions.assertArrayEquals(test4, data4);

    }
}
