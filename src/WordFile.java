public class WordFile {
    private String file;
    private Button actionButton;

    public WordFile(String file) {
        this.file = file;
        this.actionButton = new Button("Delete");
    }

    public String getFile() {
        return file;
    }

    public Button getActionButton() {
        return actionButton;
    }
}
