import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.util.ArrayList;
import java.util.List;

public class TableViewState {
    private final TableView<?> tableView;
    private List<ObservableList<String>> savedStates;
    private int currentStateIndex;
    public TableViewState(TableView<String> tableView) {
        this.tableView = tableView;
    }

    private void saveCurrentState(ObservableList<String> currentState) {
        if (savedStates == null) {
            savedStates = new ArrayList<>();
        }

        savedStates.add(FXCollections.observableArrayList(currentState));
        currentStateIndex = savedStates.size() - 1;
    }
}
