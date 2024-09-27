package com.example.foodplanner.mealofday.search.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import com.example.foodplanner.searchoption.ingredients.view.*;
import com.example.foodplanner.searchoption.categories.view.*;
import com.example.foodplanner.searchoption.countries.view.*;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.search.presenter.SearchOptionsPresenter;

public class SearchOptionsFragment extends Fragment implements SearchOptionsView {

    private SearchOptionsPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the presenter
        presenter = new SearchOptionsPresenter(this);

        // Get the buttons from the layout
        Button btnCategories = view.findViewById(R.id.btn_categories);
        Button btnCountries = view.findViewById(R.id.btn_countries);
        Button btnIngredients = view.findViewById(R.id.btn_ingredients);

        // Set click listeners to handle actions
        btnCategories.setOnClickListener(v -> presenter.onCategoryClick());
        btnCountries.setOnClickListener(v -> presenter.onCountryClick());
        btnIngredients.setOnClickListener(v -> presenter.onIngredientClick());
    }

    @Override
    public void navigateToCategories() {

        startActivity(new Intent(getActivity(), CategoriesActivity.class));
    }

    @Override
    public void navigateToCountries() {
        startActivity(new Intent(getActivity(), CountriesActivity.class));
    }

    @Override
    public void navigateToIngredients() {
        startActivity(new Intent(getActivity(), IngredientsActivity.class));
    }
}
