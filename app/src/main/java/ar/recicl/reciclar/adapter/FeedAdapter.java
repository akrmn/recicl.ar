package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ElementViewHolder> {

    private Context mContext;

    public FeedAdapter(Context context) {
        mContext = context;
    }

    @Override
    public FeedAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_feed, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedAdapter.ElementViewHolder holder, int position) {
        holder.bind(Person.anyPerson());
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public class ElementViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.circle_image_view) CircleImageView mCircleImageView;
        @Bind(R.id.text_view) TextView mTextView;
        @Bind(R.id.check_star) ImageView mCheckStar;

        boolean checked;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            checked = false;
        }

        public void bind(Person person) {
            mCircleImageView.setImageResource(person.getPictureRes());
            String base = "%s ha entregado 18kg de periódico en el Centro de Reciclaje en Recoleta";
            mTextView.setText(String.format(
                    base,
                    person.getName()
            ));
        }

        @OnClick(R.id.check_star)
        void onClickCheckStar (View v) {
            checked ^= true;
            if (checked) {
                mCheckStar.setImageResource(R.drawable.ic_star_black_24dp);
            } else {
                mCheckStar.setImageResource(R.drawable.ic_star_border_black_24dp);
            }
        }

    }
}
