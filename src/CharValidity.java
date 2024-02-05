public enum CharValidity {
    /**
     * When the character guessed is both the correct character
     * and is in the correct position in the word
     */
    CORRECT_POSITION,
    /**
     * When the character guessed is present in the word
     * but is not in the correct position
     */
    PRESENT_CHAR,
    /**
     * When the character guessed is not present in the word at all
     */
    INCORRECT,
}
