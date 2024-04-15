package main;

public class User {
    private String username;
    private String password;
    private UserType userType;

    public User(String username, String password, UserType userType) {
        this.username = username;
        this.password = password;
        this.userType = userType;
    }
    protected void setUserType(UserType userType) {
        this.userType = userType;
    }
    protected UserType getUserType() {
        return userType;
    }
    protected void setUsername(String username) {
        this.username = username;
    }
    protected String getUsername() {
        return username;
    }
    protected void setPassword(String password) {
        this.password = password;
    }
    protected String getPassword() {
        return password;
    }
}
