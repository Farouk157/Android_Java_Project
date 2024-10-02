package com.example.foodplanner.searchoption.ingredients.model;

public class Ingredient {
    private String name;
    private String imageUrl;

    public Ingredient(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getStrIngredientName() {
        return name;
    }

    public String getStrIngredientImage() {
        return imageUrl;
    }
}
