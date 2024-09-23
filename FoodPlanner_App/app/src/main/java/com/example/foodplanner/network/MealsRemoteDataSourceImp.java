package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.*;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MealsRemoteDataSourceImp implements MealsRemoteDataSource{
    private MealService mealService;

    public MealsRemoteDataSourceImp(MealService mealService) {
        this.mealService = mealService;
    }

    @Override
    public void fetchRandomMeal(NetworkCallBack<List<Meal>> callback) {
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
                        callback.onSuccess(randomMeals);
                    }
                }

                @Override
                public void onFailure(Call<MealResponse> call, Throwable t) {
                    callback.onFailure(t.getMessage());
                }
            });
        }
    }
}
