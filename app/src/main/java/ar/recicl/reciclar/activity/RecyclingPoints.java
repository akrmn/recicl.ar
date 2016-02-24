package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.FeedAdapter;
import ar.recicl.reciclar.adapter.Map;
import ar.recicl.reciclar.adapter.RecyclingPointAdapter;
import ar.recicl.reciclar.application.Application;
import butterknife.Bind;
import butterknife.OnClick;

public class RecyclingPoints extends Base {

    public static final int TYPE_GLASS = 1;
    public static final int TYPE_PLASTIC = 2;
    public static final int TYPE_CARDBOARD = 3;
    public static final int TYPE_ORGANIC = 4;

    private int[] mTitleRess = new int[]{
            0,
            R.string.label_glass,
            R.string.label_plastic,
            R.string.label_cardboard,
            R.string.label_organic_waste,
    };

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    RecyclingPointAdapter mRecyclingPointAdapter;

    private int type;

    public RecyclingPoints() {
        super(R.layout.activity_recycling_points, R.menu.recycling_points,
                R.string.title_activity_recycling_points, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        type = getIntent().getIntExtra("type", 0);
        if (type == 0) finish();

        setTitle(mTitleRess[type]);

        setupRecyclerView();
    }

    private void setupRecyclerView() {
        mRecyclingPointAdapter = new RecyclingPointAdapter(RecyclingPoints.this);
        mRecyclerView.setAdapter(mRecyclingPointAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclingPoints.this));

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .showLastDivider()
                .paint(getDividerPaint())
                .build());
    }

    @OnClick(R.id.epa)
    void onClickEpa() {
        Intent intent = new Intent(this, Map.class);
        startActivity(intent);
    }
}
