package com.example.foodplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealTypeConverter;
import com.example.foodplanner.model.Plan;

@Database(entities = {Meal.class, Plan.class}, version = 1)
@TypeConverters({MealTypeConverter.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;

    public AppDataBase() {
    }
    public abstract MealDAO mealDAO();
    public abstract PlanDAO planDAO(); // New DAO for managing Plan


    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration() // Starting fresh without migration
                    .build();
        }
        return instance;
    }
}