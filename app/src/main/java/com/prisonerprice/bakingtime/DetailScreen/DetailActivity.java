package com.prisonerprice.bakingtime.DetailScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.prisonerprice.bakingtime.Model.Ingredient;
import com.prisonerprice.bakingtime.Model.Step;
import com.prisonerprice.bakingtime.R;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private StepsListFragment stepsListFragment;
    private MediaPlayerFragment mediaPlayerFragment;
    private InstructionFragment instructionFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // phone ui
        if (savedInstanceState == null) {
            StepsListFragment stepsListFragment = new StepsListFragment();
            fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.step_list_fl, stepsListFragment).commit();
        }
    }
}
