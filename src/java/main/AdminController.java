/**
 *  Copyright 2024 SWE 2710 111 Team B (Duaa "DJ" Aljalous, Lazar Jovanovic,Theresa Kettner, Jack Rosenbecker)
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
 package main;
/**
 * Portions of this code were generated by the Github Copilot AI
 */

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

/**
 * AdminController class manages admin dashboard
 * Allows the admin to set the word length for the game
 * Allows the admin to add new word files
 * Allows the admin to switch between target and valid tables
 */
public class AdminController implements Initializable {
    @FXML
    private TextField wordLength;
    @FXML
    private TableView<FileModel> fileTable;
    @FXML
    private Button addFileBtn;
    @FXML
    private Button returnToStartBtn;

    private ObservableList<FileModel> targetFiles;
    private ObservableList<FileModel> validFiles;
    private static boolean isTargetTable = true;
    private static Button currentTargetBtn;
    private static Button currentValidBtn;
    final static Stage ADMIN_STAGE = new Stage();
    private static final String FXML_PATH = "start_page.fxml";


    /**
     * Initializes the admin dashboard
     *
     * @param url URL of the resource file
     * @param resourceBundle Resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        wordLength.addEventFilter(KeyEvent.KEY_TYPED, keyEvent -> {
            // Adds an event filter to the wordLength Textfield to only allow numbers to be entered
            if (!"0123456789".contains(keyEvent.getCharacter())) {
                keyEvent.consume();
            }
        });
        // Sets the word length when the user presses enter
        wordLength.setOnAction(actionEvent -> setWordLength());
        // Initialize the targetFiles and validFiles ObservableLists
        targetFiles = FXCollections.observableArrayList();
        validFiles = FXCollections.observableArrayList();
        // Set the items in the fileTable to targetFiles list
        fileTable.setItems(targetFiles);

        //Get the first and second columns in the fileTable
        TableColumn<FileModel, ?> fileColumn = fileTable.getColumns().get(0);
        TableColumn<FileModel, ?> actionColumn = fileTable.getColumns().get(1);

        fileColumn.setCellValueFactory(new PropertyValueFactory<>("file"));
        actionColumn.setCellValueFactory(new PropertyValueFactory<>("actionButton"));

        addFileBtn.disableProperty().bind(wordLength.textProperty().isEmpty());
    }

    /**
     * Sets the word length for the game
     */
    public void setWordLength() {
        String length = wordLength.getText();
        if (length.length() > 0) {
            main.WordBank.setWordLength(Integer.parseInt(length));
        }
    }

    /**
     * Adds a new word file to the appropriate List(targetFiles or validFiles)
     *
     */
    public void addNewWordFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select main.Word File");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                                                 new FileChooser.ExtensionFilter("CSV Files", "*.csv*"));
        FileModel fileModel = new FileModel(fileChooser.showOpenDialog(null).getName());
        if (isTargetTable) {
            targetFiles.add(fileModel);
        } else {
            validFiles.add(fileModel);
        }
    }

    /**
     * Returns to the start page
     */
    @FXML
    private void returnToStartPage() {
        /**
        try {
            FXMLLoader loader = new FXMLLoader(AdminController.class.getResource("/resources/start_page.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            ADMIN_STAGE.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
         */
    }

    /**
     * Switches the fileTables items to targetFiles and sets isTargetTable to true
     */
    public void switchToTargetTable() {
        isTargetTable = true;
        fileTable.setItems(targetFiles);
    }

    /**
     * Switches view in Admin dashboard to display valid word files
     * This is done by changing the items in the fileTable,
     * which is a TableView object, to display the validFiles list
     */
    public void switchToValidTable() {
        isTargetTable = false;
        fileTable.setItems(validFiles);
    }

    /**
     *
     */
    public static class FileModel {
        public final String file;
        public final Button actionButton;

        /**
         *
         * @param file
         */
        public FileModel(String file) {
            this.file = file;
            this.actionButton = new Button("Set");

            actionButton.setOnAction(actionEvent -> updateBank());
            updateBtnDisplay();
        }

        /**
         *
         */
        private void updateBank() {
            Path filePath = Path.of(file);
            try {
                if (isTargetTable) {
                    WordBank.updateTargetBank(filePath.toFile());
                } else {
                    WordBank.updateValidBank(filePath.toFile());
                }
            } catch (FileNotFoundException e) {
                System.out.println("File cannot be found or does not exist.");
            }
        }

        /**
         *
         */
        private void updateBtnDisplay() {
            if (isTargetTable) {
                if (currentTargetBtn != null) {
                    currentTargetBtn.setDisable(true);
                    currentTargetBtn.setVisible(false);
                }
                currentTargetBtn = actionButton;
            } else {
                if (currentValidBtn != null) {
                    currentValidBtn.setVisible(false);
                    currentValidBtn.setDisable(true);
                }
                currentValidBtn = actionButton;
            }

            actionButton.setDisable(false);
            actionButton.setVisible(true);

        }

        /**
         *
         * @return
         */
        public String getFile() {
            return file;
        }

        /**
         *
         * @return
         */
        public Button getActionButton() {
            return actionButton;
        }
    }
}
