package com.prisonerprice.bakingtime.DetailScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

import java.util.List;

public class StepsListFragment extends Fragment implements DetailScreenAdapter.StepClickListener {

    //private StepFragmentClickListener mListener;
    private RecyclerView stepRecyclerView;
    private List<Step> data;

    public StepsListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_steps_list, container, false);

        stepRecyclerView = (RecyclerView) rootView.findViewById(R.id.step_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1);
        stepRecyclerView.setLayoutManager(gridLayoutManager);
        stepRecyclerView.setHasFixedSize(true);

        data = DetailViewModel.recipe.getSteps();
        DetailScreenAdapter detailScreenAdapter = new DetailScreenAdapter(this, data);
        stepRecyclerView.setAdapter(detailScreenAdapter);

        return rootView;
    }

    @Override
    public void onItemClick(int position) {

    }

    /*
    public interface StepFragmentClickListener {
        // TODO: Update argument type and name
        void onStepFragmentClick(int position);
    }

     */
}
