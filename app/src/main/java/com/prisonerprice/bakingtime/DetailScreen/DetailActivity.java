package com.prisonerprice.bakingtime.DetailScreen;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.google.android.material.snackbar.Snackbar;
import com.prisonerprice.bakingtime.R;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = DetailActivity.class.getSimpleName();

    private FragmentManager fragmentManager;
    private DetailViewModel model = DetailViewModel.getInstance();

    private StepsListFragment stepsListFragment;
    private MediaPlayerFragment mediaPlayerFragment;
    private InstructionFragment instructionFragment;
    private PlayNextFragment playNextFragment;

    private FrameLayout listFrameLayout;
    private LinearLayout detailLinearLayout;

    public static Snackbar atTheBeginningSnack;
    public static Snackbar atTheEndSnack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        fragmentManager = getSupportFragmentManager();

        // phone ui
        listFrameLayout = (FrameLayout) findViewById(R.id.step_list_fl);
        detailLinearLayout = (LinearLayout) findViewById(R.id.detail_fragments_ll);

        atTheBeginningSnack =
                Snackbar.make(detailLinearLayout, "You are at the beginning!", 800);
        atTheEndSnack =
                Snackbar.make(detailLinearLayout, "You have done!", 800);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) setSnackColor();

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

            model.isDetailShown(false);
        }

        model.getShowDetail().observe(this, b -> {
            if (b) {
                listFrameLayout.setVisibility(View.GONE);
                detailLinearLayout.setVisibility(View.VISIBLE);
                fragmentManager.beginTransaction()
                        .replace(R.id.media_player_fl, new MediaPlayerFragment())
                        .commit();
            } else {
                listFrameLayout.setVisibility(View.VISIBLE);
                detailLinearLayout.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onBackPressed() {
        boolean b = model.getShowDetail().getValue();
        if (b) {
            listFrameLayout.setVisibility(View.VISIBLE);
            detailLinearLayout.setVisibility(View.GONE);
            model.isDetailShown(false);
        } else {
            super.onBackPressed();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void setSnackColor() {
        atTheBeginningSnack.setBackgroundTint(this.getColor(R.color.colorPrimaryLight));
        atTheEndSnack.setBackgroundTint(this.getColor(R.color.colorPrimaryLight));
    }
}
