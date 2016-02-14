package ar.recicl.reciclar.application;

import com.firebase.client.Firebase;

public class Application extends android.app.Application {

    private static Firebase sFirebase;

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

        sFirebase = new Firebase("https://boiling-heat-6697.firebaseio.com/");
    }

    public static Firebase getFirebase() {
        return sFirebase;
    }
}
