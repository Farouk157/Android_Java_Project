package com.example.foodplanner.mealsbycountry.view;

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
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.RandomMealClient;
import com.example.foodplanner.mealsbycountry.presenter.*;
import java.util.ArrayList;
import java.util.List;

public class MealsByCountryFragment extends Fragment implements MealsByCountryView {

    private MealsByCountryPresenter presenter;
    private RecyclerView recyclerView;
    private MealsByCountryAdapter adapter;
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

        // Retrieve country ID from bundle
        if (getArguments() != null) {
            String countryId = getArguments().getString("COUNTRY_ID");
            Log.d("MealsByCountryFragment", "Received countryId: " + countryId);
            // Check if the countryId is not null and then fetch meals
            if (countryId != null) {
                presenter = new MealsByCountryPresenter(this, new MealsRemoteDataSourceImp(RandomMealClient.getMealService()));
                presenter.fetchMealsForCountry(countryId);
            }
        }

        // Set up RecyclerView and adapter
        recyclerView = view.findViewById(R.id.rv_avail_meals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MealsByCountryAdapter(meals, getContext(), new MealsByCountryAdapter.OnMealClickListener() {
            @Override
            public void onMealClick(Meal meal) {
                // Handle meal click event, e.g., open meal details
                Log.d("MealsByCountryFragment", "Clicked on meal: " + meal.getStrMeal());
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void displayMealsByCountry(List<Meal> fetchedMeals) {
        Log.d("MealsByCountryFragment", "Displaying " + fetchedMeals.size() + " meals.");
        meals.clear();
        meals.addAll(fetchedMeals);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
