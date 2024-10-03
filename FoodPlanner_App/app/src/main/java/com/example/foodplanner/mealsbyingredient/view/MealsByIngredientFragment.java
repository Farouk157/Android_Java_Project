package com.example.foodplanner.mealsbyingredient.view;

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
import com.example.foodplanner.mealsbyingredient.presenter.*;
import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.RandomMealClient;

import java.util.ArrayList;
import java.util.List;
import com.example.foodplanner.database.*;
import com.example.foodplanner.mealofday.model.*;

public class MealsByIngredientFragment extends Fragment implements MealsByIngredientView {

    private MealsByIngredientPresenter presenter;
    private RecyclerView recyclerView;
    private MealsByIngredientAdapter adapter;
    private List<Meal> meals = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout
        return inflater.inflate(R.layout.fragment_meals_available, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Retrieve ingredient ID from bundle
        if (getArguments() != null) {
            String ingredientId = getArguments().getString("INGREDIENT_ID");
            Log.d("MealsByIngredientFragment", "Received ingredientId: " + ingredientId);
            // Check if the ingredientId is not null and then fetch meals
            if (ingredientId != null) {
                presenter = new MealsByIngredientPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()), MealsRemoteDataSourceImp.getInstance()));
                presenter.getMealsByIngredient(ingredientId);
            }
        }

        // Set up RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rv_avail_meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealsByIngredientAdapter(meals, getContext(), new MealsByIngredientAdapter.OnMealClickListener() {
            @Override
            public void onMealClick(Meal meal) {
                // Handle meal click event, e.g., open meal details
                Log.d("MealsByCountryFragment", "Clicked on meal: " + meal.getStrMeal());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayMealsByIngredient(List<Meal> fetchedMeals) {
        Log.d("MealsByIngredientFragment", "Displaying " + fetchedMeals.size() + " meals.");
        meals.clear();
        meals.addAll(fetchedMeals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
