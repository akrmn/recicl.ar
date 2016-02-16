package ar.recicl.reciclar.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.firebase.client.Firebase;

import ar.recicl.reciclar.R;
import butterknife.ButterKnife;

public class Register extends Base {

    public Register() {
        super(R.layout.activity_register, R.menu.register, R.string.title_activity_main, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
