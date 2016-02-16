package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Welcome extends FirebaseLoginBaseActivity {

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
        showFirebaseLoginPrompt();
    }

    @OnClick(R.id.button_register)
    void onButtonRegisterClick() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected Firebase getFirebaseRef() {
        return Application.getFirebase();
    }

    @Override
    protected void onFirebaseLoginProviderError(FirebaseLoginError firebaseLoginError) {
        // TODO: Handle an error from the authentication provider
    }

    @Override
    protected void onFirebaseLoginUserError(FirebaseLoginError firebaseLoginError) {
        // TODO: Handle an error from the user
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
