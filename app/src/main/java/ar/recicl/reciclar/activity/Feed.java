package ar.recicl.reciclar.activity;

import android.support.design.widget.Snackbar;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.widget.FAB;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Feed extends Base {

    @Bind(R.id.fab) FAB fab;
    MaterialSheetFab<FAB> mMaterialSheetFab;

    public Feed() {
        super(R.layout.activity_feed, R.menu.feed, R.string.app_name, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_feed);
        ButterKnife.bind(this);

        setupToolbar();
        setupFAB();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.feed, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
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
}
