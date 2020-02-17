package com.prisonerprice.bakingtime.DetailScreen;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.prisonerprice.bakingtime.R;

public class InstructionFragment extends Fragment {

    private DetailViewModel model = DetailViewModel.getInstance();

    public InstructionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_instruction, container, false);

        TextView instruction = (TextView) rootView.findViewById(R.id.instruction_tv);
        model.getSelectedStep().observe(getViewLifecycleOwner(), step -> {
            instruction.setText(step.getDescription());
        });

        return rootView;
    }
}
