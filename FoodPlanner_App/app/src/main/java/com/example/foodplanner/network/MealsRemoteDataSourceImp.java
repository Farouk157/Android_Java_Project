package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.search.model.CategoryResponse;
import com.example.foodplanner.search.model.CountryResponse;
import com.example.foodplanner.search.model.IngredientResponse;
import com.example.foodplanner.search.model.Category;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MealsRemoteDataSourceImp implements MealsRemoteDataSource{
    private MealService mealService;
    private static MealsRemoteDataSourceImp instance;

    private MealsRemoteDataSourceImp() {
        this.mealService = RandomMealClient.getInstance();
    }

    public static MealsRemoteDataSourceImp getInstance(){
        if(instance == null){
            instance = new MealsRemoteDataSourceImp();
        }
        return instance;
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
    public void fetchCategories(CategoryNetworkCallBack callBack) {
        List<Category>available_categories = new ArrayList<>();
        Call<CategoryResponse> call = mealService.getCategories();
        call.enqueue(new Callback<CategoryResponse>() {
            @Override
            public void onResponse(Call<CategoryResponse> call, Response<CategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
//                  available_categories.add(response.body().getMeals().get(0));
                    callBack.onCategoryCallBackSuccess(response.body().getCategories());
                } else {
                    callBack.onCategoryCallBackFailure("Error fetching Categories");
                }
            }

            @Override
            public void onFailure(Call<CategoryResponse> call, Throwable t) {
                callBack.onCategoryCallBackFailure(t.getMessage());
            }
        });
    }

    @Override
    public void fetchCountries(NetworkCallBack<List<Meal>> callBack) {
        Call<CountryResponse> call = mealService.getCountries();
        call.enqueue(new Callback<CountryResponse>() {
            @Override
            public void onResponse(Call<CountryResponse> call, Response<CountryResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> countriesWithFlags = response.body().getCountries();
                    callBack.onSuccess(countriesWithFlags);
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
    public void fetchIngredients(NetworkCallBack<List<Meal>> callBack){
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

    @Override
    public void fetchMealsByCategory(String categoryId, NetworkCallBack<List<Meal>> callback) {
        Call<MealResponse> call = mealService.getMealsByCategory(categoryId);

        call.enqueue(new Callback<MealResponse>(){
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.d("MealsByCategoryPresenter", "Fetched meals: " + meals.size());
                    callback.onSuccess(meals);
                } else {
                    callback.onFailure("Failed to fetch meals by category.");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.e("MealsByCategoryPresenter", "API call failed: " + t.getMessage());
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void fetchMealsByCountry(String countryId, NetworkCallBack<List<Meal>> callback) {
        Call<MealResponse> call = mealService.getMealsByCountry(countryId);

        call.enqueue(new Callback<MealResponse>(){
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.d("MealsByCountryPresenter", "Fetched meals: " + meals.size());
                    callback.onSuccess(meals);
                } else {
                    callback.onFailure("Failed to fetch meals by country.");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.e("MealsByCountryPresenter", "API call failed: " + t.getMessage());
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void fetchMealsByIngredient(String ingredientId, NetworkCallBack<List<Meal>> callback) {
        Call<MealResponse> call = mealService.getMealsByIngredient(ingredientId);

        call.enqueue(new Callback<MealResponse>(){
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.d("MealsByIngredientPresenter", "Fetched meals: " + meals.size());
                    callback.onSuccess(meals);
                } else {
                    callback.onFailure("Failed to fetch meals by ingredient.");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                Log.e("MealsByIngredientPresenter", "API call failed: " + t.getMessage());
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }

    @Override
    public void fetchMealByName(String mealName, NetworkCallBack<Meal> callback) {
        Call<MealResponse> call = mealService.getMealByName(mealName);
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getMeals() != null && !response.body().getMeals().isEmpty()) {
                    Meal meal = response.body().getMeals().get(0); // Get the first meal from the list
                    callback.onSuccess(meal);
                } else {
                    callback.onFailure("Failed to fetch meal details.");
                }
            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                callback.onFailure("Network error: " + t.getMessage());
            }
        });
    }
}

