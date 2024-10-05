package com.example.foodplanner.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plans_table")
public class Plan {
    @PrimaryKey
    @NonNull
    private Meal meal;   // Name of the meal related to the plan
    private String scheduledDate; // Date for scheduling the meal
    private String mealType;      // Meal type (e.g., breakfast, lunch, dinner)
    private boolean isScheduled;   // Flag to indicate if the meal is scheduled

    public Plan(@NonNull Meal meal, String scheduledDate, String mealType) {
        this.meal = meal;
        this.scheduledDate = scheduledDate;
        this.mealType = mealType;
    }

    public void setScheduled(boolean scheduled) {
        isScheduled = scheduled;
    }

    public boolean isScheduled() {
        return isScheduled;
    }

    @NonNull
    public Meal getMeal() {
        return meal;
    }

    public String getScheduledDate() {
        return scheduledDate;
    }

    public String getMealType() {
        return mealType;
    }
}
