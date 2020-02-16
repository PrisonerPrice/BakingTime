package com.prisonerprice.bakingtime.Model;

import android.app.Activity;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class RecipeUtils {

    public static String loadJSONFromAsset(Context context) {
        String json = null;
        try {
            InputStream is = context.getAssets().open("recipe.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public static List<Recipe> parseJSONToRecipes(String jsonString) throws JSONException {
        List<Recipe> result = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray array = root.getJSONArray("recipes");

        for(int i = 0; i < array.length(); i++) {
            JSONObject jsonRecipe = array.getJSONObject(i);
            int id = jsonRecipe.getInt("id");
            String recipeName = jsonRecipe.getString("name");

            JSONArray jsonIngredients = jsonRecipe.getJSONArray("ingredients");
            List<Ingredient> ingredients = new ArrayList<>();
            for(int j = 0; j < jsonIngredients.length(); j++) {
                JSONObject jsonIngredient = jsonIngredients.getJSONObject(j);
                int quantity = jsonIngredient.getInt("quantity");
                String measure = jsonIngredient.getString("measure");
                String ingredientName = jsonIngredient.getString("ingredient");
                ingredients.add(new Ingredient(quantity, measure, ingredientName));
            }

            JSONArray jsonSteps = jsonRecipe.getJSONArray("steps");
            List<Step> steps = new ArrayList<>();
            for(int m = 0; m < jsonSteps.length(); m++) {
                JSONObject jsonStep = jsonSteps.getJSONObject(m);
                int stepId = jsonStep.getInt("id");
                String shortDescription = jsonStep.getString("shortDescription");
                String description = jsonStep.getString("description");
                String videoUrl = jsonStep.getString("videoURL");
                String thumbnailURL = jsonStep.getString("thumbnailURL");
                steps.add(new Step(stepId, shortDescription, description, videoUrl, thumbnailURL));
            }

            int servings = jsonRecipe.getInt("servings");
            String imageUrl = jsonRecipe.getString("image");

            result.add(new Recipe(id, recipeName, ingredients, steps, servings, imageUrl));
        }
        return result;
    }
}
