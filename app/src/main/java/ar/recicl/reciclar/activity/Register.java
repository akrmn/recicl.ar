package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.OnClick;

public class Register extends Base {

    @Bind(R.id.input_email) EditText user_email;
    @Bind(R.id.input_password1) EditText user_password1;
    @Bind(R.id.input_password2) EditText user_password2;

    @Bind(R.id.emailWrapper)
    TextInputLayout wrapper_email;
    @Bind(R.id.password1Wrapper)
    TextInputLayout wrapper_pass1;
    @Bind(R.id.password2Wrapper)
    TextInputLayout wrapper_pass2;


    public Register() {
        super(R.layout.activity_register, R.menu.register, R.string.title_activity_main, true);
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
        getFirebase().createUser(getTextAsString(user_email), getTextAsString(user_password1), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                showSnackbarMessage(getString(R.string.create_user_success), null, null);
                finish();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                switch(firebaseError.getCode()) {
                    case -15: wrapper_email.setError(getString(R.string.invalid_mail));
                              break;
                    case -18: wrapper_email.setError(getString(R.string.taken_email));
                              break;
                    case -24: showSnackbarMessage(getString(R.string.server_error), null, null);
                              break;
                    default: showSnackbarMessage(getString(R.string.unexpected_error), null, null);
                             finish();
                }
            }
        });
    }
}
