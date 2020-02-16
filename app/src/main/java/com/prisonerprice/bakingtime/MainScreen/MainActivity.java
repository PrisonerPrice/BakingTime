package com.prisonerprice.bakingtime.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.prisonerprice.bakingtime.Model.Recipe;
import com.prisonerprice.bakingtime.Model.RecipeUtils;
import com.prisonerprice.bakingtime.R;

import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainScreenAdapter.MyClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mainScreenRecyclerView;
    private MainScreenAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set RecyclerView
        mainScreenRecyclerView = (RecyclerView) findViewById(R.id.main_rv);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, numberOfColumns());
        mainScreenRecyclerView.setLayoutManager(gridLayoutManager);
        mainScreenRecyclerView.setHasFixedSize(true);

        // Set adapter
        try {
            List<Recipe> data = RecipeUtils.parseJSONToRecipes(RecipeUtils.loadJSONFromAsset(this));
            mAdapter = new MainScreenAdapter(this, data);
        } catch (JSONException e) {
            e.printStackTrace();
            mAdapter = new MainScreenAdapter(this, null);
        }

        mainScreenRecyclerView.setAdapter(mAdapter);
    }

    private int numberOfColumns() {
        return 1;
    }

    @Override
    public void onItemClick(int position) {

    }
}
