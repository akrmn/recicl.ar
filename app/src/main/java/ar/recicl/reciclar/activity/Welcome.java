package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Welcome extends Base {

    private static final String TAG = Welcome.class.getSimpleName();

    public Welcome() {
        super(R.layout.activity_welcome, R.menu.welcome, R.string.app_name, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @OnClick(R.id.button_login)
    void onButtonLoginClick() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_register)
    void onButtonRegisterClick() {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }
}
