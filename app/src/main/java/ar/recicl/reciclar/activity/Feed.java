package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.gordonwong.materialsheetfab.MaterialSheetFab;
import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.FeedAdapter;
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.FeedItem;
import ar.recicl.reciclar.data.Message;
import ar.recicl.reciclar.data.Person;
import ar.recicl.reciclar.data.User;
import ar.recicl.reciclar.widget.FAB;
import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Feed extends Base {

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.swipeContainer) SwipeRefreshLayout mSwipeContainer;
    @Bind(R.id.fab) FAB mFab;
    @Bind(R.id.user_picture_view) CircleImageView mUserPictureView;
    @Bind(R.id.user_name_view) TextView mUserNameView;
    @Bind(R.id.user_points_view) TextView mUserPointsView;
    MaterialSheetFab<FAB> mMaterialSheetFab;

    private FeedAdapter mFeedAdapter;
    private Handler mHandler = new Handler();
    private User mUser;

    public Feed() {
        super(R.layout.activity_feed, R.menu.feed, R.string.app_name, false);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (SaveSharedPreference.getUserName(this).length() == 0) {
            Log.d("FEED", "there is no username");
            Intent intent = new Intent(this, Welcome.class);
            startActivity(intent);
            finish();
        } else {
            mUser = User.getUser(SaveSharedPreference.getUserName(this));
        }

        setupFAB();
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Picasso.with(this).load(mUser.getPictureRes()).into(mUserPictureView);
        mUserNameView.setText(mUser.getName());
        mUserPointsView.setText(getResources()
                .getQuantityString(R.plurals.recypoints, mUser.getPoints(), mUser.getPoints())
        );

        mFeedAdapter.clear();
        mFeedAdapter.addData(makeFeedList(10));
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
                final int r = Application.sRandom.nextInt(5);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFeedAdapter.addData(makeFeedList(r));
                        mSwipeContainer.setRefreshing(false);
                    }
                }, (r+1)*200);
            }
        });
        mSwipeContainer.setColorSchemeResources(
                R.color.accent,
                R.color.primary,
                R.color.primary_dark);
    }

    private List<FeedItem> makeFeedList(int n) {
        List<FeedItem> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new FeedItem(Person.anyPerson(), Message.anyMessage()));
        }
        return result;
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
        mMaterialSheetFab.hideSheet();
        Intent intent = new Intent(this, Materials.class);
        startActivity(intent);
    }

    @OnClick(R.id.fab_sheet_item_scan)
    void onClickItemScan() {
        showSnackbarMessage("Ahora se abre la vista de escanear código", null, null);
    }

    @OnClick(R.id.user_picture_view)
    void onClickCIV() {
        showSnackbarMessage("Ahora se abre la vista del perfil del usuario", null, null);
    }

    private boolean onActionProfileSelected() {
        showSnackbarMessage("Ahora se abre la vista del perfil", null, null);
        return true;
    }

    private boolean onActionLogoutSelected() {
        SaveSharedPreference.clearUserName(this);
        Intent intent = new Intent(this, Welcome.class);
        startActivity(intent);
        finish();
        return true;
    }
}
