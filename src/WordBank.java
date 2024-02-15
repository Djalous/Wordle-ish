import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordBank {
    private final List<Word> targetWords = new ArrayList<>();
    private final List<Word> validWords = new ArrayList<>();

    public WordBank() {
        updateTargetBank(new File("./wordle-official.txt"));
        updateValidBank(new File("./wordle-full.txt"));
    }

    public WordBank(File targetFile, File validFile) {
        updateTargetBank(targetFile);
        updateValidBank(validFile);
    }

    public void updateTargetBank(File targetFile) {
        try (Scanner in = new Scanner(targetFile)) {
            while (in.hasNext()) {
                String wordStr = in.nextLine();
                Word wordObj = new Word(wordStr.length());

                for (int i = 0; i < wordStr.length(); ++i) {
                    wordObj.pushChar(wordStr.charAt(i));
                }
                targetWords.add(wordObj);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public void updateValidBank(File validFile) {
        try (Scanner in = new Scanner(validFile)) {
            while (in.hasNext()) {
                String wordStr = in.nextLine();
                Word wordObj = new Word(wordStr.length());

                for (int i = 0; i < wordStr.length(); ++i) {
                    wordObj.pushChar(wordStr.charAt(i));
                }
                validWords.add(wordObj);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public boolean isValid(Word word) {
        return validWords.contains(word);
    }
}
