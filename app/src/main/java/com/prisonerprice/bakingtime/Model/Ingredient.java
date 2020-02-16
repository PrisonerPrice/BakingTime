package com.prisonerprice.bakingtime.Model;

public class Ingredient {

    int quantity;
    String measure;
    String ingredientName;

    public Ingredient(int quantity, String measure, String ingredientName) {
        this.quantity = quantity;
        this.measure = measure;
        this.ingredientName = ingredientName;
    }
}
