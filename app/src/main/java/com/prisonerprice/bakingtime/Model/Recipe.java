package com.prisonerprice.bakingtime.Model;

import java.util.List;

public class Recipe {

    int id;
    String recipeName;
    List<Ingredient> ingredients;
    List<Step> steps;
    int servings;
    String imageUrl;

    public Recipe(int id, String recipeName, List<Ingredient> ingredients, List<Step> steps, int servings, String imageUrl) {
        this.id = id;
        this.recipeName = recipeName;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
