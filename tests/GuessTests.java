import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public class GuessTests {





    @Test(expected = IllegalArgumentException.class)
    public void testStoredGuess_InvalidLength() {
        //Arrange
        GameState state = new GameState(new Word(3));
        state.setCurrentGuess(new Word("ABC"));
    }
}
