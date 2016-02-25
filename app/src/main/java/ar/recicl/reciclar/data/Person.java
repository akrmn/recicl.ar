package ar.recicl.reciclar.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class Person {
    private static List<Person> sPeople = Arrays.asList(
            new Person("Augusto", R.drawable.small_people_augusto),
            new Person("Brad Pitt", R.drawable.small_people_brad),
            new Person("El Chapo Guzmán", R.drawable.small_people_chapo),
            new Person("Muammar Gaddafi", R.drawable.small_people_gadaffi),
            new Person("Kanye West", R.drawable.small_people_kanye),
            new Person("Letizia de España", R.drawable.small_people_leti),
            new Person("Maite Delgado", R.drawable.small_people_maite),
            new Person("Bob Marley", R.drawable.small_people_marley),
            new Person("Eminem", R.drawable.small_people_mnm),
            new Person("Barack Obama", R.drawable.small_people_obama),
            new Person("Taylor Swift", R.drawable.small_people_taytay),
            new Person("Darth Vader", R.drawable.small_people_vader)
    );

    public static Person anyPerson() {
        int index = Application.sRandom.nextInt(sPeople.size());
        return sPeople.get(index);
    }

    private String mName;
    private int mPictureRes;

    public Person(String name, int pictureRes) {
        mName = name;
        mPictureRes = pictureRes;
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
