package com.example.foodplanner.model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;

public class MealTypeConverter {
    private static Gson gson = new Gson();

    @TypeConverter
    public static String mealToString(Meal meal) {
        return gson.toJson(meal);
    }

    @TypeConverter
    public static Meal stringToMeal(String mealString) {
        return gson.fromJson(mealString, Meal.class);
    }
}
