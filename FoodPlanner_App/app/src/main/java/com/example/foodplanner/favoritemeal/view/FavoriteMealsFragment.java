package com.example.foodplanner.favoritemeal.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.favoritemeal.presenter.FavoriteMealsPresnter;
import com.example.foodplanner.mealdetails.view.MealDetailsFragment;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.mealofday.model.Repository;

import java.util.ArrayList;
import java.util.List;
import com.example.foodplanner.database.*;
import com.example.foodplanner.network.*;

public class FavoriteMealsFragment extends Fragment implements FavoriteMealsView, FavoriteMealsOnClick {

    private FavoriteMealsAdapter adapter;
    private FavoriteMealsPresnter presenter;
    private RecyclerView recyclerView;
    private List<Meal> mealList = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Initialize the presenter with the repository and the view (this fragment)
        presenter = new FavoriteMealsPresnter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()), MealsRemoteDataSourceImp.getInstance()));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_meals, container, false);

        // Initialize RecyclerView and set up the adapter
        recyclerView = view.findViewById(R.id.rv_avail_meals);
        adapter = new FavoriteMealsAdapter(getContext(), mealList, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Fetch and display favorite meals
        presenter.getFavMeals(getViewLifecycleOwner());

        return view;
    }

    // Implement the displayMeals method to show meals in the RecyclerView
    @Override
    public void displayMeals(List<Meal> meals) {
        mealList.clear();
        mealList.addAll(meals);
        adapter.setMeals(mealList); // Update the adapter with the new meal list
        adapter.notifyDataSetChanged();
    }

    // Handle errors by displaying an error message (can be a Toast or Snackbar)
    @Override
    public void showError(String message) {
        // Example: Using a Toast to show the error
        android.widget.Toast.makeText(getContext(), message, android.widget.Toast.LENGTH_SHORT).show();
    }

    // Handle when a meal is clicked
    @Override
    public void onMealClick(Meal meal) {
        MealDetailsFragment mealDetailsFragment = MealDetailsFragment.newInstance(meal);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mealDetailsFragment);

        if (getActivity() != null) {
            getActivity().findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }

    // Handle removing a meal from favorites
    @Override
    public void onRemoveMealClick(Meal meal) {
        presenter.removeMealFromFavorite(meal); // Remove the meal from the repository
        mealList.remove(meal); // Remove the meal from the list
        adapter.notifyDataSetChanged(); // Notify the adapter to update the RecyclerView
    }
}
