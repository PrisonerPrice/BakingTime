package com.prisonerprice.bakingtime.DetailScreen;

import androidx.lifecycle.ViewModel;

import com.prisonerprice.bakingtime.Model.Recipe;

public class DetailViewModel extends ViewModel {

    public static DetailViewModel detailViewModel;
    private Recipe recipe;

    public static DetailViewModel getInstance() {
        if (detailViewModel == null) {
            detailViewModel = new DetailViewModel();
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
