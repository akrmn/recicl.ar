package ar.recicl.reciclar.activity;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.OnClick;

public class Login extends Base {

    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.input_password1) EditText user_password1;
    @Bind(R.id.input_password2) EditText user_password2;

    @Bind(R.id.emailWrapper) TextInputLayout wrapper_email;
    @Bind(R.id.password1Wrapper) TextInputLayout wrapper_pass1;
    @Bind(R.id.password2Wrapper) TextInputLayout wrapper_pass2;


    public Login() {
        super(R.layout.activity_login, R.menu.login, R.string.title_activity_login, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_complete_register)
    void onButtonCompleteRegisterClick() {
        boolean error = false;
        wrapper_email.setError(null);
        wrapper_pass1.setError(null);
        wrapper_pass2.setError(null);
        if(getTextAsString(user_email).equals("")){
            wrapper_email.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(getTextAsString(user_password1).equals("")){
            wrapper_pass1.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(getTextAsString(user_password2).equals("")){
            wrapper_pass2.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(!getTextAsString(user_password1).equals(getTextAsString(user_password2))){
            wrapper_pass2.setError(getString(R.string.match_pass_error));
            error = true;
        }
        if (error){
            return;
        }
    }
}
