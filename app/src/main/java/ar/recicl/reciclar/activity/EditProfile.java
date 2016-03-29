package ar.recicl.reciclar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.view.MenuItem;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import butterknife.BindString;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditProfile extends Base {

    @Bind(R.id.input_name) TextView mName;
    @Bind(R.id.input_bio) TextView mBio;
    @Bind(R.id.input_location) TextView mLocation;
    @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;

    private Person mPerson;

    public EditProfile() {
        super(R.layout.activity_edit_profile, R.menu.edit_profile, R.string.title_activity_editprofile, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = Person.getPerson(SaveSharedPreference.getUserName(this));
        mName.setText(mPerson.getName());
        mBio.setText(mPerson.getBio());
        mLocation.setText(mPerson.getLocation());
        Picasso.with(this).load(mPerson.getPictureRes()).into(mCircleImageView);
    }

    @OnClick(R.id.button_change_prof_pic)
    void OnButtonCPClick(){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"
        ));
        intent.setType("image/*");
        startActivity(intent);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_ok) {
            if(mName.getText().toString().equals("")){
                AlertDialog.Builder builder = new AlertDialog.Builder(EditProfile.this);
                builder.setMessage(getResources().getString(R.string.name_required));
                builder.setPositiveButton(getResources().getString(R.string.action_ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            } else {
                showSnackbarMessage("Done!",null,null);
                mPerson.setName(mName.getText().toString());
                mPerson.setBio(mBio.getText().toString());
                mPerson.setLocation(mLocation.getText().toString());
                Intent intent = new Intent(EditProfile.this, Profile.class);
                intent.putExtra("personId", mPerson.getEmail());
                startActivity(intent);
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }



}
