import java.util.Arrays;

public class Word {
    private char[] chars;
    private int bufferInd = 0;

    public Word(int wordSize) {
        chars = new char[wordSize];
    }

    public boolean pushChar(char c) {
        if (bufferInd >= chars.length) {
            return false;
        }

        chars[bufferInd] = c;
        bufferInd++;

        return true;
    }

    public void backspace() {
        if (bufferInd <= 0) {
            return;
        }

        chars[bufferInd] = '\u0000'; //reset char to null character
        bufferInd--;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Arrays.equals(chars, word.chars);
    }

    @Override
    public String toString() {
        return new String(chars);
    }
}
