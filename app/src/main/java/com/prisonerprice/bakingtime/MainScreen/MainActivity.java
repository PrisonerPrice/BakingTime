package com.prisonerprice.bakingtime.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.prisonerprice.bakingtime.DetailScreen.DetailActivity;
import com.prisonerprice.bakingtime.DetailScreen.DetailViewModel;
import com.prisonerprice.bakingtime.Model.Recipe;
import com.prisonerprice.bakingtime.Model.RecipeUtils;
import com.prisonerprice.bakingtime.R;

import org.json.JSONException;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainScreenAdapter.MyClickListener {

    private final static String TAG = MainActivity.class.getSimpleName();
    private RecyclerView mainScreenRecyclerView;
    private List<Recipe> data;
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
            data = RecipeUtils.parseJSONToRecipes(RecipeUtils.loadJSONFromAsset(this));
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
        Intent intent = new Intent(this, DetailActivity.class);
        DetailViewModel.getInstance(getApplication()).setRecipe(data.get(position));
        startActivity(intent);
    }
}
