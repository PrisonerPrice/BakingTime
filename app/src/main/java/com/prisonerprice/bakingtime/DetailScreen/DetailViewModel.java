package com.prisonerprice.bakingtime.DetailScreen;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.prisonerprice.bakingtime.Model.Recipe;

public class DetailViewModel extends AndroidViewModel {

    public static DetailViewModel detailViewModel;
    private Recipe recipe;

    public DetailViewModel(@NonNull Application application) {
        super(application);
    }

    public static DetailViewModel getInstance(Application application) {
        if (detailViewModel == null) {
            detailViewModel = new DetailViewModel(application);
        }
        return detailViewModel;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }
}
