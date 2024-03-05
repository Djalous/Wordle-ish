import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class WordTests {

    @Test
    public void testPush() {
        Word w1 = new Word(5);

        w1.pushChar('c');
        w1.pushChar('o');
        w1.pushChar('o');
        w1.pushChar('l');

        Assert.assertEquals(w1.toString(), "cool");

        w1.backspace();

        Assert.assertEquals(w1.toString(), "coo");

        w1.pushChar('l');
        w1.pushChar('l');

        Assert.assertEquals(w1.toString(), "cooll");
        Assert.assertFalse(w1.pushChar('l'));
        Assert.assertEquals(w1.toString(), "cooll");
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

        Assert.assertEquals(w1, w2);

        w2.backspace();

        Assert.assertNotEquals(w1, w2);

        Word w3 = new Word(6);

        w3.pushChar('c');
        w3.pushChar('o');
        w3.pushChar('o');
        w3.pushChar('l');

        Assert.assertNotEquals(w1, w3);
        Assert.assertEquals(w1.toString(), w3.toString());
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

        Assert.assertArrayEquals(test, data);

        CharValidity[] test2 = new CharValidity[] {
                CharValidity.CORRECT_POSITION,
                CharValidity.CORRECT_POSITION,
                CharValidity.PRESENT_CHAR,
                CharValidity.PRESENT_CHAR,
                CharValidity.CORRECT_POSITION,
        };

        CharValidity[] data2 = w1.getCorrect(new Word("colol"));

        Assert.assertArrayEquals(test2, data2);

        CharValidity[] test3 = new CharValidity[] {
                CharValidity.PRESENT_CHAR,
                CharValidity.CORRECT_POSITION,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
        };

        CharValidity[] data3 = w1.getCorrect(new Word("aodcf"));

        Assert.assertArrayEquals(test3, data3);

        CharValidity[] data4 = w1.getCorrect(new Word("12345"));

        CharValidity[] test4 = new CharValidity[] {
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
        };

        Assert.assertArrayEquals(test4, data4);

        Word w3 = new Word("ooooo");

        CharValidity[] data5 = w3.getCorrect(new Word("cooll"));

        CharValidity[] test5 = new CharValidity[] {
                CharValidity.PRESENT_CHAR,
                CharValidity.CORRECT_POSITION,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
                CharValidity.INCORRECT,
        };

        Assert.assertArrayEquals(test4, data4);
    }
}
