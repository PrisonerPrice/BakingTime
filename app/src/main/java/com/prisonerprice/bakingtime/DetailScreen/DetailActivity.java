package com.prisonerprice.bakingtime.DetailScreen;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.prisonerprice.bakingtime.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv = findViewById(R.id.detail_tv);
        tv.setText(DetailViewModel.getInstance(getApplication()).getRecipe().getRecipeName());
    }
}
