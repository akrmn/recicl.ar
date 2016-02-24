package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.OnClick;

public class Register extends Base {

    private static final String EMAIL_PATTERN = "^[a-zA-Z0-9#_~!$&'()*+,;=:.\"(),:;<>@\\[\\]\\\\]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*$";
    private Pattern pattern = Pattern.compile(EMAIL_PATTERN);
    private Matcher matcher;

    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.input_password1) EditText user_password1;
    @Bind(R.id.input_password2) EditText user_password2;

    @Bind(R.id.emailWrapper) TextInputLayout wrapper_email;
    @Bind(R.id.password1Wrapper) TextInputLayout wrapper_pass1;
    @Bind(R.id.password2Wrapper) TextInputLayout wrapper_pass2;

    public Register() {
        super(R.layout.activity_register, R.menu.register, R.string.title_activity_register, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @OnClick(R.id.button_complete_register)
    void onButtonCompleteRegisterClick() {
        boolean error = false;
        boolean foo = false;
        wrapper_email.setError(null);
        wrapper_pass1.setError(null);
        wrapper_pass2.setError(null);

        if(getTextAsString(user_email).equals("")){
            wrapper_email.setError(getString(R.string.required_field_error));
            foo = true;
        }
        if(getTextAsString(user_password1).equals("")){
            wrapper_pass1.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(getTextAsString(user_password2).equals("")){
            wrapper_pass2.setError(getString(R.string.required_field_error));
            error = true;
        }
        if(!getTextAsString(user_password1).equals(getTextAsString(user_password2)) && !error){
            wrapper_pass2.setError(getString(R.string.match_pass_error));
            error = true;
        }
        if(!validateEmail(getTextAsString(user_email)) && !foo){
            wrapper_email.setError(getString(R.string.invalid_mail));
            error = true;
        }
        if (error || foo){
            return;
        }

        Intent intent = new Intent(this, RegisterSuccess.class);
        startActivity(intent);
        finish();
    }

    public boolean validateEmail(String email) {
        matcher = pattern.matcher(email);
        System.out.println(matcher.matches());
        return matcher.matches();
    }
}
