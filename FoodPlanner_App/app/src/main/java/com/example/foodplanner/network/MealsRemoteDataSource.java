package com.example.foodplanner.network;

import  com.example.foodplanner.mealofday.apphome.model.*;

import java.util.List;

public interface MealsRemoteDataSource {

    void fetchRandomMeal(NetworkCallBack<List<Meal>> callback);
}
