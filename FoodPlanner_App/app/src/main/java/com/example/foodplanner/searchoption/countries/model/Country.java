package com.example.foodplanner.searchoption.countries.model;

public class Country {
    private String name;
    private String imageUrl;

    public Country(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getStrCountryName() {
        return name;
    }

    public String getStrCountryImage() {
        return imageUrl;
    }
}
