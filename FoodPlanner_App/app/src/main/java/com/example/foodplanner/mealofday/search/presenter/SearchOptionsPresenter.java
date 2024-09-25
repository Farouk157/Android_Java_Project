package com.example.foodplanner.mealofday.search.presenter;

import com.example.foodplanner.mealofday.search.view.SearchOptionsView;

public class SearchOptionsPresenter {

    private final SearchOptionsView view;

    public SearchOptionsPresenter(SearchOptionsView view) {
        this.view = view;
    }

    public void onCategoryClick() {
        // Logic for category click
        view.navigateToCategories();
    }

    public void onCountryClick() {
        // Logic for country click
        view.navigateToCountries();
    }

    public void onIngredientClick() {
        // Logic for ingredient click
        view.navigateToIngredients();
    }
}
