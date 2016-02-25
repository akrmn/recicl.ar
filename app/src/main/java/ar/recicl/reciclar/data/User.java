package ar.recicl.reciclar.data;

import java.util.HashMap;
import java.util.Map;

import ar.recicl.reciclar.R;

public class User extends Person {

    private static final Map<String, User> sUsers;

    static {
        sUsers = new HashMap<String, User>();
        sUsers.put("luke.skywalker@death.star",
                new User("Luke Skywalker", R.drawable.small_people_luke, "luke.skywalker@death.star", "iamyourfather", 156));
        sUsers.put("vgibelli@cantv.net",
                new User("Viviana Gibelli", R.drawable.small_people_viviana, "vgibelli@cantv.net", "danielsarcos", 8745));
        sUsers.put("donald@trump.com",
                new User("Donald Trump", R.drawable.small_people_trump, "donald@trump.com", "memememe", 20));
    }

    private String mEmail;
    private String mPassword;
    private int mPoints;

    public User(String name, int pictureRes, String email, String password, int points) {
        super(name, pictureRes);
        mEmail = email;
        mPassword = password;
        mPoints = points;
    }

    public int getPoints() {
        return mPoints;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public static User getUser(String email) {
        return sUsers.get(email);
    }

    public static boolean logIn(String email, String password) {
        return sUsers.containsKey(email) && sUsers.get(email).getPassword().equals(password);
    }
}
