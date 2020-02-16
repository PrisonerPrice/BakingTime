package com.prisonerprice.bakingtime.MainScreen;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.prisonerprice.bakingtime.R;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Log.d(TAG, loadJSONFromAsset(this).toString());
        try {
            Log.d(TAG, parseJSONToRecipes(loadJSONFromAsset(this)).toString());
        } catch (JSONException e) {
            Log.e(TAG, "Failed to parsing JSON String");
            e.printStackTrace();
        }

         */
    }
}
