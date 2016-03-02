package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;

public class Register extends Base {
    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.input_password1) EditText user_password1;
    @Bind(R.id.input_password2) EditText user_password2;

    @Bind(R.id.emailWrapper) TextInputLayout wrapper_email;
    @Bind(R.id.password1Wrapper) TextInputLayout wrapper_pass1;
    @Bind(R.id.password2Wrapper) TextInputLayout wrapper_pass2;

    @BindString(R.string.required_field_error) String mRequiredFieldError;
    @BindString(R.string.match_pass_error) String mMatchPassError;
    @BindString(R.string.invalid_email_error) String mInvalidEmailError;

    public Register() {
        super(R.layout.activity_register, R.menu.register, R.string.title_activity_register, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_register)
    void onButtonCompleteRegisterClick() {
        boolean error = false;
        boolean foo = false;
        wrapper_email.setError(null);
        wrapper_pass1.setError(null);
        wrapper_pass2.setError(null);

        if(getTextAsString(user_email).equals("")){
            wrapper_email.setError(mRequiredFieldError);
            foo = true;
        }
        if(getTextAsString(user_password1).equals("")){
            wrapper_pass1.setError(mRequiredFieldError);
            error = true;
        }
        if(getTextAsString(user_password2).equals("")){
            wrapper_pass2.setError(mRequiredFieldError);
            error = true;
        }
        if(!getTextAsString(user_password1).equals(getTextAsString(user_password2)) && !error){
            wrapper_pass2.setError(mMatchPassError);
            error = true;
        }
        if(!validateEmail(getTextAsString(user_email)) && !foo){
            wrapper_email.setError(mInvalidEmailError);
            error = true;
        }
        if (error || foo){
            return;
        }

        Intent intent = new Intent(this, RegisterSuccess.class);
        startActivity(intent);
        finish();
    }


}
