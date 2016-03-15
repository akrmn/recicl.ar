package ar.recicl.reciclar.data;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;

public class Achievement {
    private String mMessage;
    private int mPictureRes;

    public static int[] pictureResArray = {
            R.drawable.trophy_1,
            R.drawable.trophy_2,
            R.drawable.trophy_3,
            R.drawable.trophy_4,
            R.drawable.trophy_5,
            R.drawable.trophy_6,
            R.drawable.trophy_7,
            R.drawable.trophy_8,
            R.drawable.trophy_9
    };

    public static String[] messageArray = {
            "Primera visita al recipunto de la Avenida de Mayo",
            "¡50 botellas de plástico recicladas!",
            "Eres el Reciclador Máximo de Palermo",
            "¡100 frascos de vidrio reciclados!",
            "Motivador prominente en la región",
            "Participaste en la apertura del centro de Reciclaje «Ernesto Guevara»",
            "Alcanzaste tus metas de reciclaje durante 3 meses seguidos",
            "¡Invitaste a 20 amigos a recicl.ar!",
            "¡100 cajas de cartón recicladas!"
    };

    public Achievement () {
        mMessage = messageArray[Application.sRandom.nextInt(messageArray.length)];
        mPictureRes = pictureResArray[Application.sRandom.nextInt(pictureResArray.length)];
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public int getPictureRes() {
        return mPictureRes;
    }

    public void setPictureRes(int pictureRes) {
        mPictureRes = pictureRes;
    }
}
