package com.example.foodplanner.mealsbycategory.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealsbycategory.presenter.MealsByCategoryPresenter;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.RandomMealClient;

import java.util.ArrayList;
import java.util.List;

public class MealsByCategoryFragment extends Fragment implements MealsByCategoryView {

    private MealsByCategoryPresenter presenter;
    private RecyclerView recyclerView;
    private MealsByCategoryAdapter adapter;
    private List<Meal> meals = new ArrayList<>();  // Keep the meals list for displaying meals

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        return inflater.inflate(R.layout.fragment_meals_available, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve category ID from bundle
        if (getArguments() != null) {
            String categoryId = getArguments().getString("CATEGORY_ID");
            Log.d("MealsByCategoryFragment", "Received categoryId: " + categoryId); // Log this
            // Check if the categoryId is not null and then fetch meals
            if (categoryId != null) {
                presenter = new MealsByCategoryPresenter(this, new MealsRemoteDataSourceImp(RandomMealClient.getMealService()));
                presenter.fetchMealsForCategory(categoryId);
            }
        }

        // Set up RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rv_avail_meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealsByCategoryAdapter(meals, getContext(), new MealsByCategoryAdapter.OnMealClickListener() {
            @Override
            public void onMealClick(Meal meal) {
                // Handle meal click event, e.g., open meal details
                Log.d("MealsByCategoryFragment", "Clicked on meal: " + meal.getStrMeal());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayMealsByCategory(List<Meal> fetchedMeals) {
        // Update the meal list and notify the adapter
        Log.d("MealsByCategoryFragment", "Displaying " + fetchedMeals.size() + " meals.");
        meals.clear();
        meals.addAll(fetchedMeals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
