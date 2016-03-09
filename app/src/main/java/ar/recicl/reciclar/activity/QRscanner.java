package ar.recicl.reciclar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;

public class QRscanner extends Base implements QRCodeReaderView.OnQRCodeReadListener {

    @Bind(R.id.qrdecoderview) QRCodeReaderView mydecoderview;
    Person mPerson;
    int foo = 1;

    public QRscanner() {
        super(R.layout.activity_qrscanner, R.menu.qrscanner, R.string.title_activity_qrscanner, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = Person.getPerson(SaveSharedPreference.getUserName(this));
        mydecoderview.setOnQRCodeReadListener(this);

    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        if (text.equals("Me gusta el yogurt")){
            if (foo == 1) {
                foo = 2;
                AlertDialog.Builder builder = new AlertDialog.Builder(QRscanner.this);
                builder.setTitle("Reciclaje exitoso");
                builder.setMessage("Felicidades! Por tu reciclaje acabas de recibir 50 recipuntos!");
                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mPerson.receivePoints(50);
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        } else {
            if (foo == 1) {
                foo = 2;
                AlertDialog.Builder builder = new AlertDialog.Builder(QRscanner.this);
                builder.setTitle("Reciclaje fallido");
                builder.setMessage("Disculpe, no hemos podido reconocer el código escaneado.");
                builder.setPositiveButton("Intentar de nuevo", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        foo = 1;
                    }
                });
                builder.setNegativeButton("Salir", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }
    }

    @Override
    public void cameraNotFound() {
        showSnackbarMessage("Camara no encontrada",null,null);
        finish();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mydecoderview.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mydecoderview.getCameraManager().stopPreview();
    }
}
