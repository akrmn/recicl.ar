package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.OnClick;

public class Login extends Base {

    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.input_password) EditText user_password;

    @Bind(R.id.emailWrapper) TextInputLayout wrapper_email;
    @Bind(R.id.password1Wrapper) TextInputLayout wrapper_pass;

    public Login() {
        super(R.layout.activity_login, R.menu.login, R.string.title_activity_login, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.pass_forgot)
    void onTextPassForgotClick() {
        Intent intent = new Intent(this, PasswordForgot.class);
        startActivity(intent);
    }

    @OnClick(R.id.button_login)
    void onButtonCompleteLoginClick() {
        boolean error = false;
        wrapper_email.setError(null);
        wrapper_pass.setError(null);

        if(getTextAsString(user_email).equals("")){
            wrapper_email.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(getTextAsString(user_password).equals("")){
            wrapper_pass.setError(getString(R.string.required_field_error));
            error = true;
        }
        if (error){
            return;
        }
        Intent intent = new Intent(this, Feed.class);
        startActivity(intent);
        finish();
    }
}
