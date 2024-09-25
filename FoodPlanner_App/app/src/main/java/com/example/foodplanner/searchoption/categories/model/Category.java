package com.example.foodplanner.searchoption.categories.model;

public class Category {
    private String name;
    private String imageUrl;

    public Category(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getStrCategoryName() {
        return name;
    }

    public String getStrCategoryImage() {
        return imageUrl;
    }

}
