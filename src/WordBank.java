import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

    public void updateTargetBank(File targetFile) throws InvalidPathException {
        try (Scanner in = new Scanner(targetFile)) {
            checkFileExtension(targetFile, in);
            addToWordList(targetWords, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public void updateValidBank(File validFile) throws InvalidPathException {
        try (Scanner in = new Scanner(validFile)) {
            checkFileExtension(validFile, in);
            addToWordList(validWords, in);
        } catch (FileNotFoundException e) {
            System.out.println("File cannot be found or does not exist.");
        }
    }

    public boolean isValid(Word word) {
        if (word.toString().matches("[^A-Za-z]+")) return false;
        return validWords.contains(word);
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

    public Word generateTargetWord() {
        Random rand = new Random();
        return targetWords.get(rand.nextInt(targetWords.size()));
    }
}
