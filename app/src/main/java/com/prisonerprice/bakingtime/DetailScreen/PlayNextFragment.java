package com.prisonerprice.bakingtime.DetailScreen;

import android.content.Context;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.prisonerprice.bakingtime.R;

public class PlayNextFragment extends Fragment {

    private static final String TAG = PlayNextFragment.class.getSimpleName();

    private MaterialButton prevBtn;
    private MaterialButton nextBtn;
    private DetailViewModel model = DetailViewModel.getInstance();
    private int currentIndex;

    public PlayNextFragment() {
        // Required empty public constructor
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_play_next, container, false);

        prevBtn = rootView.findViewById(R.id.play_prev_btn);
        nextBtn = rootView.findViewById(R.id.play_next_btn);

        int maxIndex = model.getRecipe().getSteps().size() - 1;

        model.getSelectedStep().observe(getViewLifecycleOwner(), step -> {
            currentIndex = step.getId();
            Log.d(TAG, "CurrentIndex: " + currentIndex);
            if (currentIndex == 0) {
                prevBtn.setOnClickListener(v -> DetailActivity.atTheBeginningSnack.show());
                nextBtn.setOnClickListener(v -> model.selectStep(model.getRecipe().getSteps().get(currentIndex + 1)));
            } else if (currentIndex == maxIndex) {
                prevBtn.setOnClickListener(v -> model.selectStep(model.getRecipe().getSteps().get(currentIndex - 1)));
                nextBtn.setOnClickListener(v -> DetailActivity.atTheEndSnack.show());
            } else {
                prevBtn.setOnClickListener(v -> model.selectStep(model.getRecipe().getSteps().get(currentIndex - 1)));
                nextBtn.setOnClickListener(v -> model.selectStep(model.getRecipe().getSteps().get(currentIndex + 1)));
            }
        });

        return rootView;
    }
}
