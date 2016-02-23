package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.FeedAdapter;
import ar.recicl.reciclar.widget.FAB;
import butterknife.Bind;
import butterknife.OnClick;

public class Feed extends Base {

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.swipeContainer) SwipeRefreshLayout mSwipeContainer;
    @Bind(R.id.fab) FAB mFab;
    MaterialSheetFab<FAB> mMaterialSheetFab;

    private FeedAdapter mFeedAdapter;

    public Feed() {
        super(R.layout.activity_feed, R.menu.feed, R.string.app_name, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupToolbar();
        setupFAB();
        setupRecyclerView();
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
        mMaterialSheetFab = new MaterialSheetFab<>(mFab, sheetView, overlay, sheetColor, fabColor);
    }

    private void setupRecyclerView() {
        mFeedAdapter = new FeedAdapter(Feed.this);
        mRecyclerView.setAdapter(mFeedAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Feed.this));

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .showLastDivider()
                .paint(getDividerPaint())
                .build());

        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // ToDo!!!
                mSwipeContainer.setRefreshing(false);
            }
        });
        mSwipeContainer.setColorSchemeResources(
                R.color.accent,
                R.color.primary,
                R.color.primary_dark);
    }

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
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        finish();
        return true;
    }
}
