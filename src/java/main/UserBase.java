package main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserBase {
    private static final HashMap<String, User> users = new HashMap<>(){
        {put("admin", new User("admin", "admin", UserType.ADMIN));}
    };
    private static String currentUsername;

    public static void addUser(String username, String password, UserType userType) {
        users.put(username, new User(username, password, userType));
    }

    public static boolean containsUser(String username) {
        return users.containsKey(username);
    }

    public static boolean checkPassword(String username, String password) {
        if (containsUser(username)) {
            return users.get(username).getPassword().equals(password);
        }
        return false;
    }

    public static void removeUser(String username) {
        users.remove(username);
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }
}
