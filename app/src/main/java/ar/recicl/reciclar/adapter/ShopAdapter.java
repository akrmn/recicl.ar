package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.activity.Base;
import ar.recicl.reciclar.activity.Shop;
import ar.recicl.reciclar.data.SPItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ElementViewHolder> {

    private Context mContext;
    private List<SPItem> mData;
    private OnItemClickListener mOnItemClickListener;

    public ShopAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public void addData(List<SPItem> data) {
        mData.addAll(0, data);
        if (data.size() > 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public ShopAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_shopping_product, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopAdapter.ElementViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void clear() {
        mData.clear();
        notifyDataSetChanged();
    }

    public class ElementViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
        @Bind(R.id.text_name) TextView mName;
        @Bind(R.id.text_description) TextView mDescription;
        @Bind(R.id.product_price) TextView mPrice;

        SPItem mSPItem;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(SPItem SPItem) {
            String price = SPItem.getPrice() + " rps";
            mSPItem = SPItem;
            Picasso.with(mContext).load(SPItem.getPictureRes()).into(mCircleImageView);
            mName.setText(SPItem.getName());
            mDescription.setText(SPItem.getDescription());
            mPrice.setText(price);
        }

        @OnClick(R.id.buy_item)
        void onClickBuyItem(View v) {
            if (mSPItem.getPrice() > mUser.getPoints()) {
                ((Base) mContext).showSnackbarMessage("No te alcanza menor", null, null);
            } else {
                ((Base) mContext).showSnackbarMessage("Comprado!", null, null);
                mUser.pay(mSPItem.getPrice());
                ((Base) mContext).recreate();
            }
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(mSPItem.getId());
            }
        }


    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(int id);
    }
}
