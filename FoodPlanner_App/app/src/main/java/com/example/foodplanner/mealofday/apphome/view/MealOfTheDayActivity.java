package com.example.foodplanner.mealofday.apphome.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.view.*;
import com.example.foodplanner.mealofday.apphome.model.*;
import com.example.foodplanner.mealofday.apphome.presenter.*;
import com.example.foodplanner.network.*;

import java.util.ArrayList;
import java.util.List;

public class MealOfTheDayActivity extends AppCompatActivity implements MealOfTheDayView {

    private RecyclerView rvMeals;
    private MealOfTheDayAdapter mealAdapter;
    private MealOfTheDayPresenter presenter;
    private List<Meal> mealList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_of_the_day);

        rvMeals = findViewById(R.id.rv_meals);
        rvMeals.setLayoutManager(new LinearLayoutManager(this));

        mealList = new ArrayList<>();
        mealAdapter = new MealOfTheDayAdapter(mealList);

        presenter = new MealOfTheDayPresenter(this, new MealsRemoteDataSourceImp(RetrofitClient.getMealService()));
        presenter.fetchMeals();
        setupNavigation();
    }

    private void setupNavigation() {
        ImageButton btnHome = findViewById(R.id.btn_home);
        ImageButton btnSearch = findViewById(R.id.btn_search);
        ImageButton btnFavorites = findViewById(R.id.btn_favorite);

        btnHome.setOnClickListener(v -> {
            // Navigate to Home
        });

        btnSearch.setOnClickListener(v -> {
            // Navigate to Search
        });

        btnFavorites.setOnClickListener(v -> {
            // Navigate to Favorites
        });
    }

    @Override
    public void displayMeals(List<Meal> meals) {
        mealList.clear();
        mealList.addAll(meals);

//        MealOfTheDayAdapter adapter = new MealOfTheDayAdapter(meals);
//        rvMeals.setLayoutManager(new LinearLayoutManager(this));
        rvMeals.setAdapter(mealAdapter);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}