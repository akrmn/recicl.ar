package ar.recicl.reciclar.data;

import java.util.Arrays;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class RecyclingCenter {
    private static List<RecyclingCenter> mCenters = Arrays.asList(
            new RecyclingCenter("Augusto", R.drawable.people_augusto),
            new RecyclingCenter("Brad Pitt", R.drawable.people_brad),
            new RecyclingCenter("El Chapo Guzmán", R.drawable.people_chapo),
            new RecyclingCenter("Muammar Gaddafi", R.drawable.people_gadaffi),
            new RecyclingCenter("Kanye West", R.drawable.people_kanye)
    );

    public static RecyclingCenter anyRecyclingCenter() {
        int index = Application.sRandom.nextInt(mCenters.size());
        return mCenters.get(index);
    }

    private String mName;
    private int mPictureRes;

    public RecyclingCenter(String name, int pictureRes) {
        mName = name;
        mPictureRes = pictureRes;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public void setPictureRes(int pictureRes) {
        mPictureRes = pictureRes;
    }
}
