import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordBank {
    private final List<Word> TARGET_WORDS = new ArrayList<>();
    private final List<Word> VALID_WORDS = new ArrayList<>();

    public WordBank() {
        updateTargetBank(new File("./wordle-official.txt"));
        updateValidBank(new File("./wordle-full.txt"));
    }

    public WordBank(File targetFile, File validFile) {
        updateTargetBank(targetFile);
        updateValidBank(validFile);
    }

    public void updateTargetBank(File targetFile) throws InvalidPathException {
        try (Scanner in = new Scanner(targetFile)) {
            checkFileExtension(targetFile, in);
            addToWordList(TARGET_WORDS, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public void updateValidBank(File validFile) throws InvalidPathException {
        try (Scanner in = new Scanner(validFile)) {
            checkFileExtension(validFile, in);
            addToWordList(VALID_WORDS, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public boolean isValid(Word word) {
        return VALID_WORDS.contains(word);
    }

    private void addToWordList(List<Word> list, Scanner scanner) {
        while (scanner.hasNext()) {
            String wordStr = scanner.nextLine().toLowerCase();
            Word wordObj = new Word(wordStr.length());

            for (int i = 0; i < wordStr.length(); ++i) {
                wordObj.pushChar(wordStr.charAt(i));
            }
            list.add(wordObj);
        }
    }

    private void checkFileExtension(File file, Scanner scanner) {
        String filePath = file.getPath();
        if (filePath.endsWith(".csv")) {
            scanner.useDelimiter(",");
        } else if (!filePath.endsWith(".txt")) {
            throw new InvalidPathException(filePath, "Unsupported file type.");
        }
    }
}
