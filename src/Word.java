import java.util.Arrays;

public class Word {
    private int freq = 0;
    private char[] chars;

    public Word(int wordSize) {
        chars = new char[wordSize];
    }

    public boolean pushChar(char c) {
        return false; //TODO: implement me!
    }

    public void backspace() {
        //TODO: implement me!
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
        return Arrays.equals(chars, word.chars);
    }
}
