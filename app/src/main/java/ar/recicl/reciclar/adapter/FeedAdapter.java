package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
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
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ElementViewHolder> {

    private Context mContext;
    private List<FeedItem> mData;

    public FeedAdapter(Context context) {
        mData = new ArrayList<>();
        mContext = context;
    }

    public void addData(List<FeedItem> data) {
        mData.addAll(0, data);
        if (data.size() > 0) {
            notifyDataSetChanged();
        }
    }

    @Override
    public FeedAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.ElementViewHolder holder, int position) {
        holder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ElementViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
        @Bind(R.id.text_view) TextView mTextView;
        @Bind(R.id.check_star) ImageView mCheckStar;

        FeedItem mFeedItem;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(FeedItem feedItem) {
            mFeedItem = feedItem;

            Picasso.with(mContext).load(feedItem.getPictureRes()).into(mCircleImageView);
            mTextView.setText(feedItem.getMessage());
            setCheckStar(feedItem.isChecked());
        }

        @OnClick(R.id.check_star)
        void onClickCheckStar (View v) {
            setCheckStar(mFeedItem.toggleChecked());
        }

        void setCheckStar(boolean checked) {
            if (checked) {
                mCheckStar.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                mCheckStar.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        }
    }
}
