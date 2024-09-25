package com.example.foodplanner.network;

import com.example.foodplanner.mealofday.apphome.model.Meal;

import java.util.List;

public interface NetworkCallBack<T> {
    void onSuccess(T data);
    void onFailure(String errorMessage);
}