import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class WordTests {

    @Test
    public void testPush() {
        Word w1 = new Word(5);
        Word w2 = new Word(5);

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
}
