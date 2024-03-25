import main.GameState;
import main.Word;
import org.junit.Assert;
import org.junit.Test;

public class TestGuessLimiter {
    @Test(expected = IndexOutOfBoundsException.class)
    public void max6GuessTest() {
        GameState game = new GameState(new Word("cache"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));

        // Throw
        game.updateCurrentGuess(new Word("bunny"));
    }

    public void getGuessCount() {
        GameState game = new GameState(new Word("cache"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));
        game.updateCurrentGuess(new Word("bunny"));

        Assert.assertEquals(game.getGuessCount(), 6);
    }
}
