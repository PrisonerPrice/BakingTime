package com.prisonerprice.bakingtime.MainScreen;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainScreenAdapter extends RecyclerView.Adapter<MainScreenAdapter.MainScreenViewHolder> {

    @NonNull
    @Override
    public MainScreenViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MainScreenViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class MainScreenViewHolder extends RecyclerView.ViewHolder {

        public MainScreenViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
