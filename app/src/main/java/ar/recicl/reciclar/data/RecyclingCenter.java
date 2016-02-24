package ar.recicl.reciclar.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class RecyclingCenter {
    private static final Map<String, RecyclingCenter> sCenters;

    static {
        sCenters = new HashMap<String, RecyclingCenter>();
        sCenters.put("1",new RecyclingCenter("1", "Centro de Reciclaje de Recoleta, Recoleta", R.drawable.rp_1,
                "Teodoro García 3367, Ciudad Autónoma de Buenos Aires", -34.5789762,-58.4536974));
        sCenters.put("2",new RecyclingCenter("2", "Punto de Reciclaje «Augusto Pinochet», Puerto Madero", R.drawable.rp_2,
                "Julieta Lanteri 1202, Ciudad Autónoma de Buenos Aires", -34.6124018,-58.3609014));
        sCenters.put("3",new RecyclingCenter("3", "Centro de Recolección «Carlos Marx», Palermo", R.drawable.rp_3,
                "República de Eslovenia 1819, Ciudad Autónoma de Buenos Aires", -34.5695835,-58.4360193));
        sCenters.put("4",new RecyclingCenter("4", "Soluciones de Reciclaje «Eva y Juan Domingo Perón», La Boca", R.drawable.rp_4,
                "Irala 1527, Ciudad Autónoma de Buenos Aires", -34.6429606,-58.3680232));
        sCenters.put("5",new RecyclingCenter("5", "Centro de Reciclaje «Ezequiel Zamora», Avenida Nueve de Julio", R.drawable.rp_5,
                "Bernardo de Irigoyen 146, Ciudad Autónoma de Buenos Aires", -34.6100135,-58.382723));
        sCenters.put("6",new RecyclingCenter("6", "Punto de Recolección «Rosinés», Belgrano", R.drawable.rp_6,
                "Cuba 2302, Ciudad Autónoma de Buenos Aires", -34.5590154,-58.4585782));
        sCenters.put("7",new RecyclingCenter("7", "Agencia de Aseo «Cristóbal Colon», Villa Crespo", R.drawable.rp_7,
                "Murillo 780, Ciudad Autónoma de Buenos Aires", -34.6001092, -58.4467588));
    }

    private double mLat;
    private double mLong;

    public static RecyclingCenter getRecyclingCenter(String index) {
        return sCenters.get(index);
    }

    public static RecyclingCenter anyRecyclingCenter() {
        int index = Application.sRandom.nextInt(sCenters.size());
        return sCenters.get("" + index);
    }

    public String getName() {
        return mName;
    }

    private String mName;
    private int mPictureRes;
    private String mId;
    private String mAddress;


    public RecyclingCenter(String id, String name, int pictureRes, String address, double lat, double lon) {
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

    public String getId() {
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
