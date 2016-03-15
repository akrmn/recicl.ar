package ar.recicl.reciclar.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
        String text = getResources().getString(R.string.you_have) + " " + getResources()
                .getQuantityString(R.plurals.recypoints, mPerson.getPoints(), mPerson.getPoints());
        mUserPointsView.setText(text);

        mShopAdapter.clear();
        mShopAdapter.addData(makeSPList(9));
    }

    private void setupRecyclerView() {
        mShopAdapter = new ShopAdapter(Shop.this);
        mRecyclerView.setAdapter(mShopAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(Shop.this));

        mRecyclerView.addItemDecoration(getHorizontalDivider());
        mShopAdapter.setOnItemClickListener(new ShopAdapter.OnItemClickListener() {
            @Override
            public void onClick(int id) {
                SPItem SPitem_act = new SPItem(mSPList[id]);
                final int price = SPitem_act.getPrice();
                int user_points = mPerson.getPoints();
                String name = SPitem_act.getName();
                if (price > user_points) {
                    showSnackbarMessage(getResources().getString(R.string.not_enough_rps), null, null);
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(Shop.this);
                    builder.setMessage(getResources().getString(R.string.buy_verification) + " " + name + "?");
                    builder.setPositiveButton(getResources().getString(R.string.action_yes), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            showSnackbarMessage(getResources().getString(R.string.item_bought), null, null);
                            mPerson.pay(price);
                            String text = getResources().getString(R.string.you_have) + " " + getResources()
                                    .getQuantityString(R.plurals.recypoints, mPerson.getPoints(), mPerson.getPoints());
                            mUserPointsView.setText(text);
                        }
                    });
                    builder.setNegativeButton(getResources().getString(R.string.action_no), new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            return;
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
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
