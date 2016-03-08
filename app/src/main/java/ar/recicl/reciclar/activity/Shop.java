package ar.recicl.reciclar.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.adapter.RecyclingCenterAdapter;
import ar.recicl.reciclar.adapter.ShopAdapter;
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.application.SaveSharedPreference;
import ar.recicl.reciclar.data.Person;
import ar.recicl.reciclar.data.SPItem;
import ar.recicl.reciclar.data.ShoppingProduct;
import butterknife.Bind;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class Shop extends Base {

    @Bind(R.id.recycler_view) RecyclerView mRecyclerView;
    @Bind(R.id.user_picture_view) CircleImageView mUserPictureView;
    @Bind(R.id.user_name_view) TextView mUserNameView;
    @Bind(R.id.user_points_view) TextView mUserPointsView;
    private Person mPerson;
    ShopAdapter mShopAdapter;
    ShoppingProduct[] mSPList = ShoppingProduct.getShoppingProductsList();

    public Shop() {
        super(R.layout.activity_shop, R.menu.shop, R.string.title_activity_shop, true);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPerson = Person.getPerson(SaveSharedPreference.getUserName(this));
        setupRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Picasso.with(this).load(mPerson.getPictureRes()).into(mUserPictureView);
        mUserNameView.setText(mPerson.getName());
        mUserPointsView.setText(getResources()
                .getQuantityString(R.plurals.recypoints, mPerson.getPoints(), mPerson.getPoints())
        );

        mShopAdapter.clear();
        mShopAdapter.addData(makeSPList(9));
    }

    private void setupRecyclerView() {
        mShopAdapter = new ShopAdapter(Shop.this);
        mRecyclerView.setAdapter(mShopAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Shop.this));

        mRecyclerView.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .showLastDivider()
                .paint(getDividerPaint())
                .build());
        mShopAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                SPItem SPitem_act = new SPItem(mSPList[id]);
                int price = SPitem_act.getPrice();
                int user_points = mPerson.getPoints();
                if (price > user_points) {
                    showSnackbarMessage("No te alcanza :( ", null, null);
                } else {
                    showSnackbarMessage("Comprado " + SPitem_act.getName(), null, null);
                    mPerson.pay(price);
                    mUserPointsView.setText(getResources()
                            .getQuantityString(R.plurals.recypoints, mPerson.getPoints(), mPerson.getPoints())
                    );
                }
            }
        });

    }

    private List<SPItem> makeSPList(int n) {
        List<SPItem> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(new SPItem(mSPList[i]));
        }
        return result;
    }

}
