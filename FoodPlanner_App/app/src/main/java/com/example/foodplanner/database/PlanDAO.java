package com.example.foodplanner.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import com.example.foodplanner.model.Plan;

import java.util.List;

@Dao
public interface PlanDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlan(Plan plan);

    @Delete
    void deletePlan(Plan plan);

    // New methods to retrieve meals by scheduling
    @Query("SELECT * FROM plans_table WHERE scheduledDate = :date")
    LiveData<List<Plan>> getMealsByDate(String date);

    @Query("SELECT * FROM plans_table WHERE isScheduled = 1")
    LiveData<List<Plan>> getScheduledMeals();

    // New query to get meals by type
    @Query("SELECT * FROM plans_table WHERE mealType = :type")
    LiveData<List<Plan>> getMealsByType(String type);
}
