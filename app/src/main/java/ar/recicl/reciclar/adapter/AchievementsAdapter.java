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
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.data.Achievement;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class AchievementsAdapter extends RecyclerView.Adapter<AchievementsAdapter.ElementViewHolder> {
    private Context mContext;
    private List<Achievement> mData;

    public AchievementsAdapter(Context context) {
        mContext = context;

        createData();
    }

    private void createData() {
        mData = new ArrayList<>();
        int n = 1 + Application.sRandom.nextInt(9);
        for (int i = 0; i < n; i++) {
            mData.add(new Achievement());
        }
    }

    @Override
    public AchievementsAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basic, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AchievementsAdapter.ElementViewHolder holder, int position) {
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

    public class ElementViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
        @Bind(R.id.text_view) TextView mTextView;

        Achievement mAchievement;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Achievement achievement) {
            mAchievement = achievement;

            Picasso.with(mContext).load(achievement.getPictureRes()).into(mCircleImageView);
            mTextView.setText(achievement.getMessage());
        }
    }
}
