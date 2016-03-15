package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.data.Person;
import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ElementViewHolder> {
    private Context mContext;
    private List<Person> mData;
    private OnItemClickListener mOnItemClickListener;

    public FriendsAdapter(Context context, String currentPersonId) {
        mContext = context;

        createData();

        mData.remove(Person.getPerson(currentPersonId));
    }

    private void createData() {
        mData = new ArrayList<>();
        mData.addAll(Person.getAllPeople());

        Collections.sort(mData, new Comparator<Person>() {
            @Override
            public int compare(Person lhs, Person rhs) {
                return lhs.getName().compareTo(rhs.getName());
            }
        });
    }

    @Override
    public FriendsAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_basic, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FriendsAdapter.ElementViewHolder holder, int position) {
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

        Person mPerson;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        public void bind(Person person) {
            mPerson = person;

            Picasso.with(mContext).load(person.getPictureRes()).into(mCircleImageView);
            mTextView.setText(person.getName());
        }

        @Override
        public void onClick(View v) {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onClick(mPerson.getEmail());
            }
        }
    }

    public void setOnItemClickListener(final OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onClick(String id);
    }
}
