import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.io.File;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.FileChooser;
import main.AdminController;

public class AdminUploadTests {

    // Mocked FileChooser instance
    @Mock
    private FileChooser fileChooserMock;

    // Our AdminController instance under test
    private AdminController adminController;

    // Lists used by the AdminController (assuming these exist)
    private ObservableList<AdminController.FileModel> targetFiles;
    private ObservableList<AdminController.FileModel> validFiles;

    @Before
    public void setUp() {
        // Initialize mocks using Mockito
        MockitoAnnotations.initMocks(this);

        // Create a new AdminController instance
        adminController = new AdminController();

        // **Assuming there's no setter for fileChooser in AdminController, we can create one here**
        targetFiles = FXCollections.observableArrayList();
        validFiles = FXCollections.observableArrayList();
        adminController.targetFiles = targetFiles;
        adminController.validFiles = validFiles;

        // Inject the mock FileChooser into the AdminController (if there's a setter)
        // adminController.setFileChooser(fileChooserMock);  // If there's a setter method

    }

    @Test
    public void testAddNewWordFile_ValidFileSelected() {
        // Arrange (Set up the test scenario)
        File selectedFile = new File("test.txt");
        when(fileChooserMock.showOpenDialog(any())).thenReturn(selectedFile); // Mock file selection to return a specific file

        // Set the condition for adding to targetFiles
        adminController.isTargetTable = true;

        // Act (Execute the method under test)
        adminController.addNewWordFile();

        // Assert (Verify the expected behavior)
        assertTrue(adminController.targetFiles.contains(new AdminController.FileModel(selectedFile.getName())));
        // Verify the FileChooser title was set as expected
        verify(fileChooserMock).setTitle("Select main.Word File");

        // You can add assertions for extension filters if needed
    }

    @Test
    public void testAddNewWordFile_UserCancels() {
        // Arrange (Set up the test scenario)
        when(fileChooserMock.showOpenDialog(any())).thenReturn(null); // Simulate user canceling the dialog

        // Act (Execute the method under test)
        adminController.addNewWordFile();

        // Assert (Verify the expected behavior)
        assertTrue(adminController.targetFiles.isEmpty()); // Assuming targetFiles is empty initially
        assertTrue(adminController.validFiles.isEmpty()); // Assuming validFiles is empty initially
    }
}

