import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;


public class GameStateTests {
    static GameState gameState;
    @BeforeClass
    public static void setUp() {
        gameState = new GameState(new Word("Apple"));
    }
    @Test
    public void updateCurrentGuessTest() {
        gameState.updateCurrentGuess(new Word("Hello"));
        Assert.assertEquals(new Word("Hello"), gameState.getCurrentGuess());
        Assert.assertEquals(1, gameState.getGuessCount());
        gameState.updateCurrentGuess(new Word("Super"));
        Assert.assertEquals(new Word("Super"), gameState.getCurrentGuess());
        Assert.assertEquals(2, gameState.getGuessCount());
        gameState.updateCurrentGuess(new Word("Hello"));
        Assert.assertTrue(gameState.getGuessList().containsKey((new Word("Hello")).toString()));
        int testFreq = gameState.getGuessList().get(new Word("Hello").toString());
        Assert.assertEquals(2, testFreq);
        Assert.assertFalse(gameState.getGuessList().containsKey((new Word("Canoe")).toString()));
        int testFreq1 = gameState.getGuessList().get(new Word("Super").toString());
        Assert.assertEquals(1, testFreq1);
    }

}
