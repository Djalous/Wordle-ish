package main;

/**
 * A type enum for the type of user interacting with the program.
 * This will determine what functionality the user has access to.
 */
public enum UserType {
    /**
     * A regular user
     */
    USER("User"),
    MIDDLE_SCHOOL_STUDENT("Middle Schooler"),
    COLLEGE_STUDENT("High Schooler"),
    ADMIN("Admin"),
    TESTER("Tester");

    private final String user;

    UserType(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return user;
    }
}
