package ar.recicl.reciclar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.PointF;
import android.os.Bundle;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import butterknife.BindString;

public class QRscanner extends Base implements QRCodeReaderView.OnQRCodeReadListener {

    @Bind(R.id.qrdecoderview) QRCodeReaderView mDecoderView;
    private Person mPerson;
    private boolean cameraEnabled = true;

    @BindString(R.string.recycle_success_title) String mRecycleSuccessTitle;
    @BindString(R.string.recycle_failure_title) String mRecycleFailureTitle;
    @BindString(R.string.recycle_failure_message) String mRecycleFailureMessage;
    @BindString(R.string.try_again_button) String mTryAgainButton;
    @BindString(R.string.exit_button) String mExitButton;
    @BindString(R.string.error_camera_not_found) String mErrorCameraNotFound;
    @BindString(android.R.string.ok) String mOkButton;

    public QRscanner() {
        super(R.layout.activity_qrscanner, R.menu.qrscanner, R.string.title_activity_qrscanner, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = Person.getPerson(SaveSharedPreference.getUserName(this));
        mDecoderView.setOnQRCodeReadListener(this);
    }

    @Override
    public void onQRCodeRead(String text, PointF[] _) {
        if (!cameraEnabled) return;

        mDecoderView.getCameraManager().stopPreview();

        String[] words = text.split("\\s");
        if (words[0].equals("recicl.ar")){
            int points = Integer.parseInt(words[1]);
            cameraEnabled = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(QRscanner.this);
            builder.setTitle(mRecycleSuccessTitle);
            builder.setMessage(getResources()
                    .getQuantityString(R.plurals.recycle_success_message, points, points)
            );
            builder.setPositiveButton(mOkButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mPerson.earnPoints(50);
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        } else {
            cameraEnabled = false;
            AlertDialog.Builder builder = new AlertDialog.Builder(QRscanner.this);
            builder.setTitle(mRecycleFailureTitle);
            builder.setMessage(mRecycleFailureMessage);
            builder.setPositiveButton(mTryAgainButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    cameraEnabled = true;
                    mDecoderView.getCameraManager().startPreview();
                }
            });
            builder.setNegativeButton(mExitButton, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        }
    }

    @Override
    public void cameraNotFound() {
        showSnackbarMessage(mErrorCameraNotFound, null, null);
        finish();
    }

    @Override
    public void QRCodeNotFoundOnCamImage() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mDecoderView.getCameraManager().startPreview();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDecoderView.getCameraManager().stopPreview();
    }
}
