package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.FeedItem;
import ar.recicl.reciclar.data.RPItem;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclingPointAdapter extends RecyclerView.Adapter<RecyclingPointAdapter.ElementViewHolder> {

    private Context mContext;
    private List<RPItem> mData;
    private OnItemClickListener mOnItemClickListener;

    public RecyclingPointAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public void addData(List<RPItem> data) {
        mData.addAll(0, data);
        if (data.size() > 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclingPointAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recycling_point, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclingPointAdapter.ElementViewHolder holder, int position) {
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

        RPItem mRPItem;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(RPItem rpItem) {
            mRPItem = rpItem;

            Picasso.with(mContext).load(rpItem.getPictureRes()).into(mCircleImageView);
            mTextView.setText(rpItem.getMessage());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(mRPItem.getId());
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
