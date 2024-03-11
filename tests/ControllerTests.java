import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

public class ControllerTests {
    static Controller controller;
    @BeforeClass
    public static void setUp() throws FileNotFoundException {
        controller = new Controller();
    }

    @Test
    public void testErrorMessages() {
        controller.setFirstTextField("A");
        controller.setSecondTextField("P");
        Assert.assertFalse(controller.validateGuess());
        Assert.assertEquals("Please enter a " + controller.getWordLength() + " letter word.",
                controller.getLabelText());
        controller.setThirdTextField("P");
        controller.setFourthTextField("Z");
        controller.setFifthTextField("E");
        Assert.assertFalse(controller.validateGuess());
        Assert.assertEquals("Please enter a valid word.", controller.getLabelText());
        controller.setFourthTextField("L");
        Assert.assertTrue(controller.validateGuess());
    }
}
