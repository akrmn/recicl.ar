package ar.recicl.reciclar.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ar.recicl.reciclar.R;
import ar.recicl.reciclar.application.Application;
import ar.recicl.reciclar.data.HistoryItem;
import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ElementViewHolder> {
    private List<HistoryItem> mData;

    public HistoryAdapter() {
        createData();
    }

    private void createData() {
        mData = new ArrayList<>();
        int n = 1 + Application.sRandom.nextInt(9);
        for (int i = 0; i < n; i++) {
            mData.add(new HistoryItem());
        }
    }

    @Override
    public HistoryAdapter.ElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_history, parent, false);
        return new ElementViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ElementViewHolder holder, int position) {
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

        @Bind(R.id.points_view) TextView mPointsView;
        @Bind(R.id.text_view) TextView mTextView;

        HistoryItem mHistoryItem;

        public ElementViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(HistoryItem historyItem) {
            mHistoryItem = historyItem;

            mPointsView.setText("+" + historyItem.getPoints() + " rps");
            mTextView.setText(historyItem.getMessageRes());
        }
    }
}
