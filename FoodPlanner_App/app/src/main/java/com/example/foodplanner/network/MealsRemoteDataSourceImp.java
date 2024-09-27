package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.*;
import com.example.foodplanner.searchoption.categories.model.Category;
import com.example.foodplanner.searchoption.categories.model.*;
import com.example.foodplanner.searchoption.countries.model.*;
import com.example.foodplanner.searchoption.ingredients.model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealsRemoteDataSourceImp implements MealsRemoteDataSource{
    private MealService mealService;

    public MealsRemoteDataSourceImp(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public void fetchRandomMeal(NetworkCallBack<List<Meal>> callBack) {
        List<Meal> randomMeals = new ArrayList<>();
        int numberOfMealsToFetch = 10;
        for (int i = 0; i < numberOfMealsToFetch; i++) {
            Call<MealResponse> call = mealService.getRandomMeal();
            call.enqueue(new Callback<MealResponse>() {
                @Override
                public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        randomMeals.add(response.body().getMeals().get(0));
                    }

                    // Check if we've fetched enough meals
                    if (randomMeals.size() == numberOfMealsToFetch) {
                        callBack.onSuccess(randomMeals);
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {
                    callBack.onFailure(t.getMessage());
                }
            });
        }
    }

    @Override
    public void fetchCategories(NetworkCallBack<List<Category>> callBack) {
        List<Category>available_categories = new ArrayList<>();
        Call<CategoryResponse> call = mealService.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
//                  available_categories.add(response.body().getMeals().get(0));
                    callBack.onSuccess(response.body().getCategories());
                } else {
                    callBack.onFailure("Error fetching Categories");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void fetchCountries(NetworkCallBack<List<Country>> callBack) {
        Call<CountryResponse> call = mealService.getCountries();
        call.enqueue(new Callback<CountryResponse>(){

            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onSuccess(response.body().getCountries());
                } else {
                    callBack.onFailure("Error fetching Countries");
                }
            }

            @Override
            public void onFailure(Call<CountryResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });
    }

    @Override
    public void fetchIngredients(NetworkCallBack<List<Ingredient>> callBack){
        Call<IngredientResponse> call = mealService.getIngredients();
        call.enqueue(new Callback<IngredientResponse>(){

            @Override
            public void onResponse(Call<IngredientResponse> call, Response <IngredientResponse> response){
                if(response.isSuccessful() && response.body() != null){
                    callBack.onSuccess(response.body().getIngredients());
                } else{
                    callBack.onFailure("Error fetching Ingredients");
                }

            }

            @Override
            public void onFailure(Call<IngredientResponse> call, Throwable t) {
                callBack.onFailure(t.getMessage());
            }
        });

    }
}

