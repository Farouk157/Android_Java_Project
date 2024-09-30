package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.MealResponse;
import com.example.foodplanner.searchoption.categories.model.*;
import com.example.foodplanner.searchoption.countries.model.*;
import com.example.foodplanner.searchoption.ingredients.model.*;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    //"list.php?c=list"
    @GET("categories.php")
    Call<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();

}
