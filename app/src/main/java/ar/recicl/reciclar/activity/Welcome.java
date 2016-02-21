package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Welcome extends FirebaseLoginBaseActivity {

    private static final String TAG = Welcome.class.getSimpleName();

    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setEnabledAuthProvider(AuthProviderType.FACEBOOK);
        setEnabledAuthProvider(AuthProviderType.PASSWORD);
    }

    @OnClick(R.id.button_login)
    void onButtonLoginClick() {
        //showFirebaseLoginPrompt();
        Application.getFirebase().authWithPassword("prueba@prueba.prueba", "prueba", new Firebase.AuthResultHandler() {
            @Override
            public void onAuthenticated(AuthData authData) {
                System.out.println("User ID: " + authData.getUid() + ", Provider: " + authData.getProvider());
            }
            @Override
            public void onAuthenticationError(FirebaseError firebaseError) {
                // there was an error
            }
        });
    }

    @OnClick(R.id.button_register)
    void onButtonRegisterClick() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    @Override
    protected Firebase getFirebaseRef() {
        return Application.getFirebase();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        Log.e(TAG, "ProviderError: " + firebaseLoginError);
    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        Log.e(TAG, "UserError: " + firebaseLoginError);
    }

    @Override
    public void onFirebaseLoggedIn(AuthData authData) {
        Intent intent = new Intent(this, Feed.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onFirebaseLoggedOut() {
        super.onFirebaseLoggedOut();
        // TODO: Handle logout
    }
}
