package ar.recicl.reciclar.data;

import android.util.Log;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class Person {
    private static final Map<String, Person> sPeople;
    static {
        sPeople = new HashMap<String, Person>();
        sPeople.put("luke.skywalker@death.star", new Person("Luke Skywalker", R.drawable.small_people_luke, "luke.skywalker@death.star", "iamyourfather", 156));
        sPeople.put("vgibelli@cantv.net", new Person("Viviana Gibelli", R.drawable.small_people_viviana, "vgibelli@cantv.net", "danielsarcos", 8745));
        sPeople.put("donald@trump.com", new Person("Donald Trump", R.drawable.small_people_trump, "donald@trump.com", "memememe", 200000));
        sPeople.put("a", new Person("Augusto", R.drawable.small_people_augusto, "a", "", 514215));
        sPeople.put("b", new Person("Brad Pitt", R.drawable.small_people_brad, "b", "", 756786));
        sPeople.put("c", new Person("El Chapo Guzmán", R.drawable.small_people_chapo, "c", "", 45886));
        sPeople.put("d", new Person("Muammar Gaddafi", R.drawable.small_people_gadaffi, "d", "", 9564));
        sPeople.put("e", new Person("Kanye West", R.drawable.small_people_kanye, "e", "", 7885));
        sPeople.put("f", new Person("Letizia de España", R.drawable.small_people_leti, "f", "", 4511));
        sPeople.put("g", new Person("Maite Delgado", R.drawable.small_people_maite, "g", "", 2354));
        sPeople.put("h", new Person("Bob Marley", R.drawable.small_people_marley, "h", "", 8945));
        sPeople.put("i", new Person("Eminem", R.drawable.small_people_mnm, "i", "", 567889));
        sPeople.put("j", new Person("Barack Obama", R.drawable.small_people_obama, "j", "", 12315));
        sPeople.put("k", new Person("Taylor Swift", R.drawable.small_people_taytay, "k", "", 45678));
        sPeople.put("l", new Person("Darth Vader", R.drawable.small_people_vader, "l", "", 1345678));
    }

    private String mEmail;
    private String mPassword;
    private int mPoints;
    private String mName;
    private int mPictureRes;

    public static Person anyPerson() {
        int index = Application.sRandom.nextInt(sPeople.size());
        return (Person)sPeople.values().toArray()[index];
    }

    public Person(String name, int pictureRes, String email, String password, int points) {
        mName = name;
        mPictureRes = pictureRes;
        mEmail = email;
        mPassword = password;
        mPoints = points;
    }

    public String getEmail() {
        return mEmail;
    }

    public String getPassword() {
        return mPassword;
    }

    public static Person getPerson(String email) {
        return sPeople.get(email);
    }

    public int getPoints() {
        return mPoints;
    }

    public void pay(int points) { mPoints = mPoints - points; }

    public static boolean logIn(String email, String password) {
        return sPeople.containsKey(email) && sPeople.get(email).getPassword().equals(password);
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public void setPictureRes(int pictureRes) {
        mPictureRes = pictureRes;
    }
}
