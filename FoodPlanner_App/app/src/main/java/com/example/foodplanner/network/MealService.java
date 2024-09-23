package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.MealResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MealService {
    @GET("random.php")
    Call<MealResponse> getRandomMeal();
}
