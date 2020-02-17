package com.prisonerprice.bakingtime.DetailScreen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

import java.util.List;

public class DetailScreenAdapter extends RecyclerView.Adapter<DetailScreenAdapter.DetailScreenViewHolder> {

    private final StepClickListener sClickListener;
    private List<Step> data;

    public DetailScreenAdapter(StepClickListener sClickListener, List<Step> data) {
        this.sClickListener = sClickListener;
        this.data = data;
    }

    @NonNull
    @Override
    public DetailScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.step_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);
        DetailScreenViewHolder detailScreenViewHolder = new DetailScreenViewHolder(view);
        return detailScreenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DetailScreenViewHolder holder, int position) {
        if (data != null && data.size() != 0) {
            Step step = data.get(position);
            String shortDescription = step.getShortDescription();
            holder.stepCardText.setText(shortDescription);
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    class DetailScreenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView stepCardText;

        public DetailScreenViewHolder(@NonNull View itemView) {
            super(itemView);
            stepCardText = (TextView) itemView.findViewById(R.id.step_card_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            sClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface StepClickListener {
        void onItemClick(int position);
    }
}
