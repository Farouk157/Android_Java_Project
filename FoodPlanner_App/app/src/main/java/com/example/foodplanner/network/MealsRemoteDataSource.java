package com.example.foodplanner.network;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealsRemoteDataSource {

    void fetchRandomMeal(NetworkCallBack<List<Meal>> callback);
    void fetchCategories(CategoryNetworkCallBack callBack);
    void fetchCountries(NetworkCallBack<List<Meal>> callBack);
    void fetchIngredients(NetworkCallBack<List<Meal>> callBack);
    void fetchMealsByCategory(String categoryId, NetworkCallBack<List<Meal>> callback);
    void fetchMealsByCountry(String countryId,NetworkCallBack<List<Meal>> callback);
    void fetchMealsByIngredient(String ingredientId,NetworkCallBack<List<Meal>> callback);
    void fetchMealByName(String mealName, NetworkCallBack<Meal> callback);
}
