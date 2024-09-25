package com.example.foodplanner.mealofday.apphome.view;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.mealofday.apphome.presenter.MealOfTheDayPresenter;
import com.example.foodplanner.mealofday.mealdetails.view.MealDetailsFragment;
import com.example.foodplanner.network.RandomMealClient;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import com.example.foodplanner.mealofday.search.view.*;

public class MealOfTheDayActivity extends AppCompatActivity implements MealOfTheDayView, MealOfTheDayAdapter.OnMealClickListener {

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
        mealAdapter = new MealOfTheDayAdapter(mealList, this, this);

        rvMeals.setAdapter(mealAdapter);

        presenter = new MealOfTheDayPresenter(this, new MealsRemoteDataSourceImp(RandomMealClient.getMealService()));
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
            SearchOptionsFragment searchFragment = new SearchOptionsFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, searchFragment);
            findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
            transaction.addToBackStack(null);
            transaction.commit();
        });

        btnFavorites.setOnClickListener(v -> {
            // Navigate to Favorites
        });
    }

    @Override
    public void displayMeals(List<Meal> meals) {
        mealList.clear();
        mealList.addAll(meals);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onMealClick(Meal meal) {
        // When a meal is clicked, show the details in a fragment
        MealDetailsFragment mealDetailsFragment = MealDetailsFragment.newInstance(meal);  // Passing meal (Serializable)
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mealDetailsFragment);  // Replace with fragment container ID
        // Show the fragment container
        findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        transaction.addToBackStack(null);  // Allow user to go back
        transaction.commit();
    }


}
