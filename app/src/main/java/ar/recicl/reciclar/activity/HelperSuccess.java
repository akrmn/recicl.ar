package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ar.recicl.reciclar.R;
import butterknife.Bind;
import butterknife.OnClick;

public class HelperSuccess extends Base {

    @Bind(R.id.label_helper_result) TextView mHelperResultLabel;
    @Bind(R.id.button_find_centers_label) TextView mButtonFindCentersLabel;
    @Bind(R.id.button_find_centers) RelativeLayout mButtonFindCenters;

    int mColor;
    int mIntType;
    String mResultString;
    String mFindCentersString;
    String mInfoContentString;

    public HelperSuccess() {
        super(R.layout.activity_helper_success, R.menu.helper, R.string.title_activity_helper, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        char type = getIntent().getCharExtra("type", 'k');
        switch (type) {
            case 'k':
                finish();
                break;
            case 'g':
                mColor = ContextCompat.getColor(this, R.color.button_glass);
                mResultString = getString(R.string.label_glass);
                mInfoContentString = getString(R.string.content_glass);
                mIntType = 1;
                break;
            case 'p':
                mColor = ContextCompat.getColor(this, R.color.button_plastic);
                mResultString = getString(R.string.label_plastic);
                mInfoContentString = getString(R.string.content_plastic);
                mIntType = 2;
                break;
            case 'c':
                mColor = ContextCompat.getColor(this, R.color.button_cardboard);
                mResultString = getString(R.string.label_cardboard);
                mInfoContentString = getString(R.string.content_cardboard);
                mIntType = 3;
                break;
            case 'o':
                mColor = ContextCompat.getColor(this, R.color.button_organic_waste);
                mResultString = getString(R.string.label_organic_waste);
                mInfoContentString = getString(R.string.content_organic_waste);
                mIntType = 4;
                break;

        }

        mFindCentersString = getString(R.string.button_helper_find_centers, mResultString);

        mHelperResultLabel.setText(mResultString);
        mHelperResultLabel.setTextColor(mColor);
        mButtonFindCentersLabel.setText(mFindCentersString);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mButtonFindCenters.setBackgroundTintList(ColorStateList.valueOf(mColor));
        }
    }

    @OnClick(R.id.button_find_centers)
    void onClickFindCenters() {
        Intent intent = new Intent(this, RecyclingCenters.class);
        intent.putExtra("type", mIntType);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.button_info)
    void onClickInfo() {
        showAlert(mResultString, mInfoContentString);
    }
}
