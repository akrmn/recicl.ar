package ar.recicl.reciclar.application;

import java.util.Random;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static final Random sRandom = new Random();
}
