package com.prisonerprice.bakingtime.MainScreen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.prisonerprice.bakingtime.Model.Recipe;
import com.prisonerprice.bakingtime.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.MainScreenViewHolder> {

    private final MyClickListener mClickListener;
    private List<Recipe> data;

    private static final String TAG = MainScreenAdapter.class.getSimpleName();

    public MainScreenAdapter(MyClickListener clickListener, List<Recipe> data) {
        this.mClickListener = clickListener;
        this.data = data;
    }

    @NonNull
    @Override
    public MainScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutId = R.layout.list_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(layoutId, parent, shouldAttachToParentImmediately);
        MainScreenViewHolder mainScreenViewHolder = new MainScreenViewHolder(view);
        return mainScreenViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MainScreenViewHolder holder, int position) {
        if (data != null && data.size() != 0) {
            Recipe recipe = data.get(position);
            String name = recipe.getRecipeName();
            String imageUrl = recipe.getImageUrl();
            holder.cardText.setText(name);
            Picasso.get().setLoggingEnabled(true);
            try {
                Picasso.get()
                        .load(imageUrl)
                        .into(holder.cardImage);
            } catch (IllegalArgumentException e) {
                Log.w(TAG, "The picture url is empty");
            }

        }
    }

    @Override
    public int getItemCount() {
        if (data == null) return 0;
        return data.size();
    }

    class MainScreenViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView cardImage;
        TextView cardText;

        public MainScreenViewHolder(@NonNull View itemView) {
            super(itemView);
            cardImage = (ImageView) itemView.findViewById(R.id.card_image);
            cardText = (TextView) itemView.findViewById(R.id.card_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            mClickListener.onItemClick(getAdapterPosition());
        }
    }

    public interface MyClickListener{
        void onItemClick(int position);
    }
}
