package com.example.foodplanner.search.presenter;

import android.util.Log;

import com.example.foodplanner.mealofday.model.*;
import com.example.foodplanner.search.view.SearchOptionsView;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.search.model.Category;
import com.example.foodplanner.network.*;
import java.util.List;

public class SearchOptionsPresenter implements NetworkCallBack<List<Meal>>, CategoryNetworkCallBack {

    private final SearchOptionsView view;
    private final Repository repository;

    // Enum to keep track of the current operation (countries or ingredients)
    private enum FetchType {
        COUNTRIES,
        INGREDIENTS
    }

    private FetchType currentFetchType;

    public SearchOptionsPresenter(SearchOptionsView view, Repository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void getCategories() {
        repository.fetchAllCategories(this);
    }

    public void getCountries() {
        currentFetchType = FetchType.COUNTRIES;
        repository.fetchCountries(this);
    }

    public void getIngredients() {
        currentFetchType = FetchType.INGREDIENTS;
        repository.fetchIngredients(this);
    }

    // Handle category callback
    @Override
    public void onCategoryCallBackSuccess(List<Category> categoryList) {
        if (categoryList != null) {
            view.displayCategories(categoryList);
        } else {
            Log.e("CategoryPresenter", "categories is null or empty");
        }
    }

    @Override
    public void onCategoryCallBackFailure(String errorMessage) {
        view.showError(errorMessage);
    }

    // Handle countries and ingredients callback (both use List<Meal> as the return type)
    @Override
    public void onSuccess(List<Meal> data) {
        if (currentFetchType == FetchType.COUNTRIES) {
            view.displayCountries(data);  // Update view with countries
        } else if (currentFetchType == FetchType.INGREDIENTS) {
            view.displayIngredients(data);  // Update view with ingredients
        }
    }

    @Override
    public void onFailure(String errorMessage) {
        view.showError(errorMessage);
    }
}
