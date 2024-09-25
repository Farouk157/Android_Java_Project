package com.example.foodplanner.network;

import  com.example.foodplanner.mealofday.apphome.model.*;
import com.example.foodplanner.searchoption.categories.model.*;
import com.example.foodplanner.searchoption.ingredients.model.*;
import com.example.foodplanner.searchoption.countries.model.*;

import java.util.List;
import java.util.Locale;

public interface MealsRemoteDataSource {

    void fetchRandomMeal(NetworkCallBack<List<Meal>> callback);
    void fetchCategories(NetworkCallBack<List<Category>> callBack);
    void fetchCountries(NetworkCallBack<List<Country>> callBack);
    void fetchIngredients(NetworkCallBack<List<Ingredient>> callBack);
}
