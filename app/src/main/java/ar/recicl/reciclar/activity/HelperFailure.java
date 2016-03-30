package ar.recicl.reciclar.activity;

import android.content.Intent;

import ar.recicl.reciclar.R;
import butterknife.OnClick;

public class HelperFailure extends Base {

    public HelperFailure() {
        super(R.layout.activity_helper_failure, R.menu.helper, R.string.title_activity_helper, true);
    }

    @OnClick(R.id.button_try_again)
    void onClickTryAgain() {
        Intent intent = new Intent(this, Helper.class);
        startActivity(intent);

        finish();
    }
}
