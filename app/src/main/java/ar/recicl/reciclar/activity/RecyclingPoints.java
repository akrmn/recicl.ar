package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.RecyclingPointAdapter;
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.data.RPItem;
import ar.recicl.reciclar.data.RecyclingCenter;
import butterknife.Bind;

public class RecyclingPoints extends Base {

    public static final int TYPE_GLASS = 1;
    public static final int TYPE_PLASTIC = 2;
    public static final int TYPE_CARDBOARD = 3;
    public static final int TYPE_ORGANIC = 4;

    @Bind(R.id.input_description) EditText mDescriptionEditText;
    @Bind(R.id.input_amount) EditText mAmountEditText;

    @Bind(R.id.wrapper_description) TextInputLayout mDescriptionWrapper;
    @Bind(R.id.wrapper_amount) TextInputLayout mAmountWrapper;

    private int[] mTitleRess = new int[]{
            0,
            R.string.label_glass,
            R.string.label_plastic,
            R.string.label_cardboard,
            R.string.label_organic_waste,
    };

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.recycler_title) TextView mRecyclerTitle;

    RecyclingPointAdapter mRecyclingPointAdapter;

    public RecyclingPoints() {
        super(R.layout.activity_recycling_points, R.menu.recycling_points,
                R.string.title_activity_recycling_points, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) finish();

        setTitle(mTitleRess[type]);
        mRecyclerTitle.setText(String.format(
                getString(R.string.recycler_title),
                getString(mTitleRess[type]).toLowerCase()
        ));

        setupRecyclerView();
        mRecyclingPointAdapter.addData(makeRPList(Application.sRandom.nextInt(3) + 3));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupRecyclerView() {
        mRecyclingPointAdapter = new RecyclingPointAdapter(RecyclingPoints.this);
        mRecyclerView.setAdapter(mRecyclingPointAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclingPoints.this));

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .showLastDivider()
                .paint(getDividerPaint())
                .build());

        mRecyclingPointAdapter.setOnItemClickListener(new RecyclingPointAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                boolean error = false;
                if (getTextAsString(mDescriptionEditText).equals("")) {
                    mDescriptionWrapper.setError(getString(R.string.required_field_error));
                    error = true;
                }
                if (getTextAsString(mAmountEditText).equals("")) {
                    mAmountWrapper.setError(getString(R.string.required_field_error));
                    error = true;
                }
                if (error) {
                    return;
                } else {
                    mAmountWrapper.setError(null);
                    mDescriptionWrapper.setError(null);
                    Intent intent = new Intent(RecyclingPoints.this, Map.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }

    private List<RPItem> makeRPList(int n) {
        List<RPItem> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new RPItem(
                    RecyclingCenter.anyRecyclingCenter(),
                    ("" + (i+1) + "." + (Application.sRandom.nextInt(99) + 1))
            ));
        }
        return result;
    }
}
