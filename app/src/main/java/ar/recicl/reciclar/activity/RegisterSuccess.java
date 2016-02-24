package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import butterknife.Bind;
import butterknife.OnClick;

import ar.recicl.reciclar.R;

public class RegisterSuccess extends Base {

    public RegisterSuccess() {
        super(R.layout.activity_register_success, R.menu.register, R.string.title_activity_register_success, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_ok)
    void onButtonOkClick() {
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        finish();
    }
}
