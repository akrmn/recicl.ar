package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.EditText;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.User;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;

public class Login extends Base {

    @Bind(R.id.input_email) EditText mEmailEditText;
    @Bind(R.id.input_password) EditText mPasswordEditText;

    @Bind(R.id.emailWrapper) TextInputLayout mEmailWrapper;
    @Bind(R.id.password1Wrapper) TextInputLayout mPasswordWrapper;

    @BindString(R.string.required_field_error) String mRequiredFieldError;

    private Handler mHandler = new Handler();

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
        mEmailWrapper.setError(null);
        mPasswordWrapper.setError(null);

        final String email = getTextAsString(mEmailEditText);
        final String password = getTextAsString(mPasswordEditText);

        if(email.equals("")){
            mEmailWrapper.setError(mRequiredFieldError);
            error = true;
        }
        if(password.equals("")){
            mPasswordWrapper.setError(mRequiredFieldError);
            error = true;
        }
        if (!error){
            setProgressBarVisibility(View.VISIBLE);
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    setProgressBarVisibility(View.GONE);
                    if (User.logIn(email, password)) {
                        SaveSharedPreference.setUserName(Login.this, email);
                        Intent intent = new Intent(Login.this, Feed.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    } else {
                        showAlert(R.string.error, R.string.invalid_email_password);
                    }
                }
            }, 1000);
        }
    }
}
