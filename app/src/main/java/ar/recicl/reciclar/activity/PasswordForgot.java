package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;
import ar.recicl.reciclar.R;

public class PasswordForgot extends Base {

    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.emailWrapper) TextInputLayout wrapper_email;

    @BindString(R.string.invalid_email_error) String mInvalidMail;

    public PasswordForgot() {
        super(R.layout.activity_password_forgot, R.menu.register, R.string.title_activity_password_forgot, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_continue)
    void onButtonOkClick() {
        if(!validateEmail(getTextAsString(user_email))){
            wrapper_email.setError(mInvalidMail);
            return;
        }
        Toast.makeText(this, R.string.email_sent, Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        finish();
    }
}
