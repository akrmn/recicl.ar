package ar.recicl.reciclar.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class Person {
    private static final Map<String, Person> sPeople;
    static {
        sPeople = new HashMap<>();
        sPeople.put("luke.skywalker@death.star", new Person("Luke Skywalker", R.drawable.people_luke, "luke.skywalker@death.star", "iamyourfather","Tatooine",  "Mi fuerza es el reciclaje.", 156));
        sPeople.put("vgibelli@cantv.net", new Person("Viviana Gibelli", R.drawable.people_viviana, "vgibelli@cantv.net", "danielsarcos", "Palermo, Buenos Aires", "La guerra de reciclar.", 8745));
        sPeople.put("donald@trump.com", new Person("Donald Trump", R.drawable.people_trump, "donald@trump.com", "memememe", "Puerto Madero, Buenos Aires", "Una muralla de material reciclado.", 200000));
        sPeople.put("a", new Person("Augusto", R.drawable.people_augusto, "a", "", "Flloresta, Buenos Aires", "Encontré a Buenos Aires una ciudad de basureros y la dejé con centros de reciclaje.", 514215));
        sPeople.put("b", new Person("Brad Pitt", R.drawable.people_brad, "b", "", "Caballito, Buenos Aires", "Reciclar es increible.", 756786));
        sPeople.put("c", new Person("El Chapo Guzmán", R.drawable.people_chapo, "c", "", "Caballito, Buenos Aires", "Reciclaré.", 45886));
        sPeople.put("d", new Person("Muammar Gaddafi", R.drawable.people_gadaffi, "d", "", "Belgrano, Buenos Aires", "Desde aquí, reciclo.", 9564));
        sPeople.put("e", new Person("Kanye West", R.drawable.people_kanye, "e", "", "Flloresta, Buenos Aires", "Recycle, bro.", 7885));
        sPeople.put("f", new Person("Letizia de España", R.drawable.people_leti, "f", "", "Belgrano, Buenos Aires", "Los españoles tenemos mucho que aprender del reciclaje en Argentina.", 4511));
        sPeople.put("g", new Person("Maite Delgado", R.drawable.people_maite, "g", "", "Puerto Madero, Buenos Aires", "Amiga, aprende a reciclar con recicl.ar.", 2354));
        sPeople.put("h", new Person("Bob Marley", R.drawable.people_marley, "h", "", "Belgrano, Buenos Aires", "Recyclize it.", 8945));
        sPeople.put("i", new Person("Eminem", R.drawable.people_mnm, "i", "", "Caballito, Buenos Aires", "Would you recycle it, or just let it slip?", 567889));
        sPeople.put("j", new Person("Barack Obama", R.drawable.people_obama, "j", "", "La Boca, Buenos Aires", "Progress, Hope, Change, Recycling.", 12315));
        sPeople.put("k", new Person("Taylor Swift", R.drawable.people_taytay, "k", "", "Recoleta, Buenos Aires", "We're never ever recycling together.", 45678));
        sPeople.put("l", new Person("Darth Vader", R.drawable.people_vader, "l", "", "Death Star", "[breath]", 1345678));
    }

    public static Collection<Person> getAllPeople() {
        return sPeople.values();
    }

    private String mName;
    private int mPictureRes;
    private String mEmail;
    private String mPassword;
    private String mLocation;
    private String mBio;
    private int mPoints;

    public static Person anyPerson() {
        int index = Application.sRandom.nextInt(sPeople.size());
        return (Person)sPeople.values().toArray()[index];
    }

    public Person(String name, int pictureRes, String email, String password, String location, String bio, int points) {
        mName = name;
        mPictureRes = pictureRes;
        mEmail = email;
        mPassword = password;
        mLocation = location;
        mBio = bio;
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

    public void earnPoints(int points) {mPoints = mPoints + points;}

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

    public String getLocation() {
        return mLocation;
    }

    public void setLocation(String location) {
        mLocation = location;
    }

    public String getBio() {
        return mBio;
    }

    public void setBio(String bio) {
        mBio = bio;
    }
}
