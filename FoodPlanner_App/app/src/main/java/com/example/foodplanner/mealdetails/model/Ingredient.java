package com.example.foodplanner.mealdetails.model;

import java.io.Serializable;

public class Ingredient implements Serializable {
        private String name;
        private String measure;
        private String imageUrl; // URL for the ingredient image

        public Ingredient(String name, String measure) {
            this.name = name;
            this.measure = measure;
        }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
