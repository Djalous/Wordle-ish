import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * A unit class which handles the comparison of words and their current state.
 * @author Jack Rosenbecker
 * @version created on 2/8/24
 */
public class Word {
    private final char[] chars;
    private int bufferInd = 0;

    /** Construct a word of a given size.
     * @param wordSize The number of characters the word will contain.
     */
    public Word(int wordSize) {
        chars = new char[wordSize];
    }

    /** Creates a word based on the given input string. NOTE: The max accepted word size will be the
     * size of the input string.
     * @param word String to construct a word out of.
     */
    public Word(String word) {
        chars = word.toLowerCase().toCharArray();
        bufferInd = word.length();
    }

    /** Adds a char to the character buffer
     * @param c Char to add
     * @return If the buffer is full and was not able to add the char this will be false.
     * Otherwise, will be true if successful
     */
    public boolean pushChar(char c) {
        if (bufferInd >= chars.length) {
            return false;
        }

        chars[bufferInd] = Character.toLowerCase(c);
        bufferInd++;

        return true;
    }

    /**
     * Removes the last character in the buffer, if there is a character there
     */
    public void backspace() {
        if (bufferInd <= 0) {
            return;
        }

        chars[bufferInd] = '\u0000'; //reset char to null character
        bufferInd--;
    }

    /** Returns the per character correctness comparing the target word as the correct word to
     * the current word.
     * @param targetWord Correct word to compare to
     * @return An array of each character's own correctness
     */
    public CharValidity[] getCorrect(Word targetWord) {
        String wordStr = this.toString();
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

    /** Verifies the two objects are equal in value
     * @param o Other object/word to compare against
     * @return True if they're equal, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;

        if (word.chars.length != this.chars.length) {
            return false;
        }

        return word.toString().equals(this.toString());
    }

    /** Get the word as a string
     * @return The string of the current chars in the word
     */
    @Override
    public String toString() {
        if (bufferInd <= 0) {
            return "";
        }

        return new String(Arrays.copyOfRange(chars, 0, bufferInd)).toLowerCase();
    }

}
