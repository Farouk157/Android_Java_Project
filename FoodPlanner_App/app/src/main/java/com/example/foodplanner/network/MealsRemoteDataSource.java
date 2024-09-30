package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.searchoption.categories.model.*;
import com.example.foodplanner.searchoption.countries.model.*;
import com.example.foodplanner.searchoption.ingredients.model.*;

import java.util.List;

public interface MealsRemoteDataSource {

    void fetchRandomMeal(NetworkCallBack<List<Meal>> callback);
    void fetchCategories(NetworkCallBack<List<Category>> callBack);
    void fetchCountries(NetworkCallBack<List<Meal>> callBack);
    void fetchIngredients(NetworkCallBack<List<Meal>> callBack);
}
