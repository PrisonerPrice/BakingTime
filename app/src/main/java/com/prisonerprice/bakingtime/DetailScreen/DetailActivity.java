package com.prisonerprice.bakingtime.DetailScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LiveData;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
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
    private PlayNextFragment playNextFragment;

    private FrameLayout listFrameLayout;
    private LinearLayout detailLinearLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        DetailViewModel model = DetailViewModel.getInstance();
        fragmentManager = getSupportFragmentManager();

        // phone ui
        listFrameLayout = (FrameLayout) findViewById(R.id.step_list_fl);
        detailLinearLayout = (LinearLayout) findViewById(R.id.detail_fragments_ll);
        model.isDetailShown(false);
        model.getShowDetail().observe(this, b -> {
            if (b) {
                listFrameLayout.setVisibility(View.GONE);
                detailLinearLayout.setVisibility(View.VISIBLE);
            } else {
                listFrameLayout.setVisibility(View.VISIBLE);
                detailLinearLayout.setVisibility(View.GONE);
            }
        });

        if (savedInstanceState == null) {
            stepsListFragment = new StepsListFragment();
            mediaPlayerFragment = new MediaPlayerFragment();
            instructionFragment = new InstructionFragment();
            playNextFragment = new PlayNextFragment();

            fragmentManager.beginTransaction()
                    .add(R.id.step_list_fl, stepsListFragment)
                    .add(R.id.media_player_fl, mediaPlayerFragment)
                    .add(R.id.instruction_fl, instructionFragment)
                    .add(R.id.play_next_fl, playNextFragment)
                    .commit();
        }
    }
}
