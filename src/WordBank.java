import java.io.File;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class WordBank {
    private List<Word> targetWords = new ArrayList<>();
    private List<Word> validWords = new ArrayList<>();

    public WordBank() {

    }

    public WordBank(File targetFile, File validFile) {
        //TODO: Implement me!
    }

    public void updateTargetBank(String targetFile) {
        //TODO: Implement me!
    }

    public void updateValidBank(String validFile) {
        //TODO: Implement me!
    }
}
