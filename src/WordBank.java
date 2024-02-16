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

    public CharValidity[] getCorrect(Word word, Word targetWord) {
        String wordStr = word.toString();
        String targetStr = targetWord.toString();

        int length = wordStr.length();

        if (length > targetStr.length()) {
            throw new InvalidParameterException("Target word length is shorter than the passed in word.");
        }

        CharValidity[] correctness = new CharValidity[length];

        for (int i = 0; i < length; i++) {
            char c1 = wordStr.charAt(i);
            char c2 = targetStr.charAt(i);

            if (c1 == c2) {
                correctness[i] = CharValidity.CORRECT_POSITION;
            }
            else if (targetStr.indexOf(c1) != -1) {
                correctness[i] = CharValidity.PRESENT_CHAR;
            }
            else {
                correctness[i] = CharValidity.INCORRECT;
            }
        }

        return correctness;
    }
}
