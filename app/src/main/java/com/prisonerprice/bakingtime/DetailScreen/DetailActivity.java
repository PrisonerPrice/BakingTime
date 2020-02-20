package com.prisonerprice.bakingtime.DetailScreen;

import androidx.annotation.RequiresApi;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.prisonerprice.bakingtime.Model.Recipe;
import com.prisonerprice.bakingtime.Model.RecipeUtils;
import com.prisonerprice.bakingtime.R;
import com.prisonerprice.bakingtime.Widget.IngredientWidget;

import org.jetbrains.annotations.TestOnly;
import org.json.JSONException;

import java.util.List;

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

        // used for testing
        if (model.getRecipe() == null) {
            int position = 0; // position = 0, 1, 2, 3
            try {
                List<Recipe> recipes = RecipeUtils.parseJSONToRecipes(RecipeUtils.loadJSONFromAsset(this));
                model.setRecipe(recipes.get(position));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

        TextView toolBarTitle = (TextView) findViewById(R.id.toolbar_title);
        if (toolBarTitle != null) toolBarTitle.setText(model.getRecipe().getRecipeName().toUpperCase());

        // update widget data
        Intent intent = new Intent(this, IngredientWidget.class);
        intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        int[] ids = AppWidgetManager.getInstance(getApplication())
                .getAppWidgetIds(new ComponentName(getApplication(), IngredientWidget.class));
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(intent);

        fragmentManager = getSupportFragmentManager();

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

        // tablet ui
        if (findViewById(R.id.sw_blank_fl) != null) {
            model.getShowDetail().observe(this, b -> {
                if (b) {
                    detailLinearLayout.setVisibility(View.VISIBLE);
                    fragmentManager.beginTransaction()
                            .replace(R.id.media_player_fl, new MediaPlayerFragment())
                            .commit();
                } else {
                    detailLinearLayout.setVisibility(View.INVISIBLE);
                }
            });
        }

        // phone ui
        else {
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
