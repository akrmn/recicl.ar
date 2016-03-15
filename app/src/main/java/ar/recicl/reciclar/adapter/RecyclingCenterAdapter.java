package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.RCItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclingCenterAdapter extends RecyclerView.Adapter<RecyclingCenterAdapter.ElementViewHolder> {

    private Context mContext;
    private List<RCItem> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclingCenterAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public void addData(List<RCItem> data) {
        mData.addAll(0, data);
        if (data.size() > 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclingCenterAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basic, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclingCenterAdapter.ElementViewHolder holder, int position) {
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
        @Bind(R.id.text_view) TextView mTextView;

        RCItem mRCItem;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(this);
        }

        public void bind(RCItem RCItem) {
            mRCItem = RCItem;

            Picasso.with(mContext).load(RCItem.getPictureRes()).into(mCircleImageView);
            mTextView.setText(RCItem.getMessage());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(mRCItem.getId());
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
