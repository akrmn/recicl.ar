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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.RecyclingCenterAdapter;
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.data.RCItem;
import ar.recicl.reciclar.data.RecyclingCenter;
import butterknife.Bind;
import butterknife.BindString;

public class RecyclingCenters extends Base {

    public static final int TYPE_GLASS = 1;
    public static final int TYPE_PLASTIC = 2;
    public static final int TYPE_CARDBOARD = 3;
    public static final int TYPE_ORGANIC = 4;

    @Bind(R.id.input_description) EditText mDescriptionEditText;
    @Bind(R.id.input_amount) EditText mAmountEditText;

    @Bind(R.id.wrapper_description) TextInputLayout mDescriptionWrapper;
    @Bind(R.id.wrapper_amount) TextInputLayout mAmountWrapper;

    @BindString(R.string.recycler_title) String mRecyclerTitle;
    @BindString(R.string.required_field_error) String mRequiredFieldError;

    private int[] mTitleRess = new int[]{
            0,
            R.string.label_glass,
            R.string.label_plastic,
            R.string.label_cardboard,
            R.string.label_organic_waste,
    };

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.recycler_title) TextView mRecyclerLabel;

    RecyclingCenterAdapter mRecyclingCenterAdapter;

    public RecyclingCenters() {
        super(R.layout.activity_recycling_centers, R.menu.recycling_centers,
                R.string.title_activity_recycling_centers, true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int type = getIntent().getIntExtra("type", 0);
        if (type == 0) finish();

        setTitle(mTitleRess[type]);
        mRecyclerLabel.setText(String.format(
                mRecyclerTitle,
                getString(mTitleRess[type]).toLowerCase()
        ));

        setupRecyclerView();
        mRecyclingCenterAdapter.addData(makeRPList(Application.sRandom.nextInt(3) + 3));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setupRecyclerView() {
        mRecyclingCenterAdapter = new RecyclingCenterAdapter(RecyclingCenters.this);
        mRecyclerView.setAdapter(mRecyclingCenterAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(RecyclingCenters.this));

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .showLastDivider()
                .paint(getDividerPaint())
                .build());

        mRecyclingCenterAdapter.setOnItemClickListener(new RecyclingCenterAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                boolean error = false;
                if (getTextAsString(mDescriptionEditText).equals("")) {
                    mDescriptionWrapper.setError(mRequiredFieldError);
                    error = true;
                }
                if (getTextAsString(mAmountEditText).equals("")) {
                    mAmountWrapper.setError(mRequiredFieldError);
                    error = true;
                }
                if (error) {
                    return;
                } else {
                    mAmountWrapper.setError(null);
                    mDescriptionWrapper.setError(null);
                    Intent intent = new Intent(RecyclingCenters.this, Map.class);
                    intent.putExtra("id", id);
                    startActivity(intent);
                }
            }
        });
    }

    private List<RCItem> makeRPList(int n) {
        List<RCItem> result = new ArrayList<>();

        List<RecyclingCenter> centers = Arrays.asList(RecyclingCenter.sCenters);
        Collections.shuffle(centers);

        for (int i = 0; i < n; i++) {
            result.add(new RCItem(
                    centers.get(i),
                    ("" + (i+1) + "." + (Application.sRandom.nextInt(99) + 1))
            ));
        }
        return result;
    }
}
