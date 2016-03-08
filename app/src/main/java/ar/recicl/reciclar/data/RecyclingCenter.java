package ar.recicl.reciclar.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class RecyclingCenter {
//    private static final Map<int, RecyclingCenter> sCenters;

    private static final RecyclingCenter[] sCenters = new RecyclingCenter[]{
            new RecyclingCenter(0, "Centro de Reciclaje de Recoleta, Recoleta", R.drawable.small_rp_0,
                    "Teodoro García 3367, Ciudad Autónoma de Buenos Aires", -34.5789762,-58.4536974),
            new RecyclingCenter(1, "Punto de Reciclaje «Augusto Pinochet», Puerto Madero", R.drawable.small_rp_1,
                    "Julieta Lanteri 1202, Ciudad Autónoma de Buenos Aires", -34.6124018,-58.3609014),
            new RecyclingCenter(2, "Centro de Recolección «Carlos Marx», Palermo", R.drawable.small_rp_2,
                    "República de Eslovenia 1819, Ciudad Autónoma de Buenos Aires", -34.5695835,-58.4360193),
            new RecyclingCenter(3, "Soluciones de Reciclaje «Eva y Juan Domingo Perón», La Boca", R.drawable.small_rp_3,
                    "Irala 1527, Ciudad Autónoma de Buenos Aires", -34.6429606,-58.3680232),
            new RecyclingCenter(4, "Centro de Reciclaje «Ezequiel Zamora», Avenida Nueve de Julio", R.drawable.small_rp_4,
                    "Bernardo de Irigoyen 146, Ciudad Autónoma de Buenos Aires", -34.6100135,-58.382723),
            new RecyclingCenter(5, "Punto de Recolección «Rosinés», Belgrano", R.drawable.small_rp_5,
                    "Cuba 2302, Ciudad Autónoma de Buenos Aires", -34.5590154,-58.4585782),
            new RecyclingCenter(6, "Agencia de Aseo «Cristóbal Colon», Villa Crespo", R.drawable.small_rp_6,
                    "Murillo 780, Ciudad Autónoma de Buenos Aires", -34.6001092, -58.4467588)
    };

    public static RecyclingCenter getRecyclingCenter(int index) {
        return sCenters[index];
    }

    public static RecyclingCenter anyRecyclingCenter() {
        int index = Application.sRandom.nextInt(sCenters.length);
        return sCenters[index];
    }

    public String getName() {
        return mName;
    }

    private String mName;
    private int mPictureRes;
    private int mId;
    private String mAddress;
    private double mLat;
    private double mLong;


    public RecyclingCenter(int id, String name, int pictureRes, String address, double lat, double lon) {
        mName = name;
        mPictureRes = pictureRes;
        mId = id;
        mAddress = address;
        mLat = lat;
        mLong = lon;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public void setPictureRes(int pictureRes) {
        mPictureRes = pictureRes;
    }

    public int getId() {
        return mId;
    }

    public String getAddress() {
        return mAddress;
    }

    public double getLat() {
        return mLat;
    }

    public double getLong() {
        return mLong;
    }
}