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
}
