package com.example.foodplanner.network;

import android.net.ConnectivityManager;

import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.search.model.Category;

import java.util.List;

public interface MealsRemoteDataSource {

    void fetchRandomMeal(NetworkCallBack<List<Meal>> callback);
    void fetchCategories(NetworkCallBack<List<Category>> callBack);
    void fetchCountries(NetworkCallBack<List<Meal>> callBack);
    void fetchIngredients(NetworkCallBack<List<Meal>> callBack);
    void fetchMealsByCategory(String categoryId, NetworkCallBack<List<Meal>> callback);
    void fetchMealsByCountry(String countryId,NetworkCallBack<List<Meal>> callback);
    void fetchMealsByIngredient(String ingredientId,NetworkCallBack<List<Meal>> callback);
}
