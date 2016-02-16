package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.firebase.client.Firebase;
import com.gordonwong.materialsheetfab.MaterialSheetFab;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.widget.FAB;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Feed extends Base {

    private Firebase mRef;

    @Bind(R.id.fab) FAB fab;
    MaterialSheetFab<FAB> mMaterialSheetFab;

    public Feed() {
        super(R.layout.activity_feed, R.menu.feed, R.string.app_name, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();
        setupFAB();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (item.getItemId()) {
            case R.id.action_profile:
                return onActionProfileSelected();
            case R.id.action_logout:
                return onActionLogoutSelected();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    private void setupFAB() {
        View sheetView = findViewById(R.id.fab_sheet);
        View overlay = findViewById(R.id.dim_overlay);
        int sheetColor = ContextCompat.getColor(this, R.color.background_card);
        int fabColor = ContextCompat.getColor(this, R.color.accent);

        // Initialize material sheet FAB
        mMaterialSheetFab = new MaterialSheetFab<>(fab, sheetView, overlay, sheetColor, fabColor);
    }

    @Override
    public void onBackPressed() {
        if (mMaterialSheetFab.isSheetVisible()) {
            mMaterialSheetFab.hideSheet();
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.fab_sheet_item_recycle)
    void onClickItemRecycle() {
        showSnackbarMessage("Ahora se abre la vista de reciclar", null, null);
    }

    @OnClick(R.id.fab_sheet_item_scan)
    void onClickItemScan() {
        showSnackbarMessage("Ahora se abre la vista de escanear código", null, null);
    }

    private boolean onActionProfileSelected() {
        showSnackbarMessage("Ahora se abre la vista del perfil", null, null);
        return true;
    }

    private boolean onActionLogoutSelected() {
        getFirebase().unauth();
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        finish();
        return true;
    }
}
