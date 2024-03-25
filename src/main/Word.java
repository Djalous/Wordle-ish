package main;

import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;

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
        //TODO Test failed for nonalpha
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

        HashMap<Character, Integer> targetFreq = targetWord.getCharFrequency();
        HashMap<Character, Integer> currentFreq = new HashMap<>();

        int length = wordStr.length();

        if (length > targetStr.length()) {
            throw new InvalidParameterException("Target word length is shorter than the passed in word.");
        }

        CharValidity[] correctness = new CharValidity[length];

        // Correct position pass
        // TODO: Refactor, this can be refactored in such way to likely do functional programming to do each pass
        // as a functional operation. That's a lot of work though and is going to require an almost complete
        // rewrite of this function. This is fine for now, even if its not the cleanest solution
        for (int i = 0; i < length; i++) {
            char c1 = wordStr.charAt(i);
            char c2 = targetStr.charAt(i);

            currentFreq.putIfAbsent(c1, 0);
            int wordCount = currentFreq.get(c1);

            currentFreq.put(c1, wordCount + 1);
            if (c1 == c2) {
                correctness[i] = CharValidity.CORRECT_POSITION;
                currentFreq.put(c1, wordCount - 1);
            }
        }

        for (int i = 0; i < length; i++) {
            if (correctness[i] == CharValidity.CORRECT_POSITION) {
                continue;
            }

            char c1 = wordStr.charAt(i);
            if (targetFreq.get(c1) == null || currentFreq.get(c1) == 0) {
                correctness[i] = CharValidity.INCORRECT;
            } else {
                correctness[i] = CharValidity.PRESENT_CHAR;
                currentFreq.put(c1, currentFreq.get(c1) - 1);
            }
        }

        return correctness;
    }

    /** Gets the per character frequencies of a word.
     * Note: this is currently private, feel free to publicize this method if need this. Remove this note
     * from this method if you publicize it :)
     * @return Hashmap of the character and its frequency.
     */
    private HashMap<Character, Integer> getCharFrequency() {
        var freq = new HashMap<Character, Integer>();

        for (int i = 0; i < chars.length; i++) {
            Integer value = freq.get(chars[i]);

            if (value == null) {
                freq.put(chars[i], 1);
            }
            else {
                freq.put(chars[i], value + 1);
            }
        }

        return freq;
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
