package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.*;
import com.example.foodplanner.searchoption.categories.model.*;
import com.example.foodplanner.searchoption.countries.model.*;
import com.example.foodplanner.searchoption.ingredients.model.*;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();

    @GET("list.php?c=list")
    Call<CategoryResponse> getCategories();

    @GET("list.php?a=list")
    Call<CountryResponse> getCountries();

    @GET("list.php?i=list")
    Call<IngredientResponse> getIngredients();

}
