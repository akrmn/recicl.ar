package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.widget.EditText;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.Map;

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
        if(getTextAsString(user_email).equals("")){
            wrapper_email.setError("Este campo es obligatorio.");
            return;
        }
        if(getTextAsString(user_password1).equals("")){
            wrapper_pass1.setError("Este campo es obligatorio.");
            return;
        }
        if(getTextAsString(user_password2).equals("")){
            wrapper_pass2.setError("Este campo es obligatorio.");
            return;
        }
        if(!getTextAsString(user_password1).equals(getTextAsString(user_password2))){
            wrapper_pass2.setError("Las contraseñas ingresadas no coinciden");
            return;
        }
        getFirebase().createUser(getTextAsString(user_email), getTextAsString(user_password1), new Firebase.ValueResultHandler<Map<String, Object>>() {
            @Override
            public void onSuccess(Map<String, Object> result) {
                System.out.println("Successfully created user account with uid: " + result.get("uid"));
                showSnackbarMessage("Cuenta creada satisfactoriamente!", null, null);
                finish();
            }
            @Override
            public void onError(FirebaseError firebaseError) {
                switch(firebaseError.getCode()) {
                    case -15: wrapper_email.setError("El email ingresado no es válido.");
                              break;
                    case -18: showSnackbarMessage("El email ingresado ya se encuentra registrado.", null, null);
                              break;
                    case -24: showSnackbarMessage("Recicl.ar no pudo conectarse con el servido :(", null, null);
                              break;
                    default: showSnackbarMessage("Ups! Ha ocurrido un error inesperado.", null, null);
                             break;
                }
            }
        });
    }
}
