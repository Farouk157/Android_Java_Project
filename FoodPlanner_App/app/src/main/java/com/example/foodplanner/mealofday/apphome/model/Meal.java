package com.example.foodplanner.mealofday.apphome.model;

import java.io.Serializable;

public class Meal implements Serializable {
    private String strMeal;
    private String strArea;
    private String strInstructions;
    private String strMealThumb;
    private String strYoutube;
    private String[] ingredients; // array of ingredient names
    private String[] ingredientImages; // array of ingredient images

    // Default serial version ID (optional but recommended)
    private static final long serialVersionUID = 1L;

    // Getters and setters for all fields
    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrArea() {
        return strArea;
    }

    public void setStrArea(String strArea) {
        this.strArea = strArea;
    }

    public String getStrInstructions() {
        return strInstructions;
    }

    public void setStrInstructions(String strInstructions) {
        this.strInstructions = strInstructions;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    public String getStrYoutube() {
        return strYoutube;
    }

    public void setStrYoutube(String strYoutube) {
        this.strYoutube = strYoutube;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public void setIngredients(String[] ingredients) {
        this.ingredients = ingredients;
    }

    public String[] getIngredientImages() {
        return ingredientImages;
    }

    public void setIngredientImages(String[] ingredientImages) {
        this.ingredientImages = ingredientImages;
    }
}
