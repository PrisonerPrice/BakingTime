package com.prisonerprice.bakingtime.DetailScreen;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.prisonerprice.bakingtime.Model.Recipe;
import com.prisonerprice.bakingtime.Model.Step;

public class DetailViewModel extends ViewModel {

    public static DetailViewModel detailViewModel;
    private Recipe recipe;
    private MutableLiveData<Boolean> showDetail = new MutableLiveData<>();
    private MutableLiveData<Step> selectedStep = new MutableLiveData<>();

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

    public LiveData<Step> getSelectedStep() {
        return selectedStep;
    }

    public void selectStep(Step step) {
        selectedStep.setValue(step);
    }

    public MutableLiveData<Boolean> getShowDetail() {
        return showDetail;
    }

    public void isDetailShown(boolean b) {
        showDetail.setValue(b);
    }
}
