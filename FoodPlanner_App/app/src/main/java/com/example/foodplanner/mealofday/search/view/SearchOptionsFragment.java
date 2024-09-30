package com.example.foodplanner.mealofday.search.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.mealofday.search.presenter.SearchOptionsPresenter;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.RandomMealClient;
import com.example.foodplanner.searchoption.categories.model.Category;
import com.example.foodplanner.searchoption.categories.view.CategoryAdapter;
import com.example.foodplanner.searchoption.ingredients.view.IngredientAdapter;
import com.example.foodplanner.searchoption.countries.view.*;

import java.util.ArrayList;
import java.util.List;

public class SearchOptionsFragment extends Fragment implements SearchOptionsView {

    private SearchOptionsPresenter presenter;

    private RecyclerView catRecyclerView;
    private RecyclerView countryRecyclerView;
    private RecyclerView ingredientRecyclerView;

    private List<Category> categoryList;
    private List<Meal> countryList;
    private List<Meal> ingredientList;

    private CountryAdapter countryAdapter;
    private CategoryAdapter catAdapter;
    private IngredientAdapter ingAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_options, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        catRecyclerView = view.findViewById(R.id.rv_categories);
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryList = new ArrayList<>();
        catAdapter = new CategoryAdapter(categoryList);
        catRecyclerView.setAdapter(catAdapter);

        countryRecyclerView = view.findViewById(R.id.rv_countries);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        countryList = new ArrayList<>();
        countryAdapter = new CountryAdapter(countryList);
        countryRecyclerView.setAdapter(countryAdapter);

        ingredientRecyclerView = view.findViewById(R.id.rv_ingredients);
        ingredientRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false));
        ingredientList = new ArrayList<>();
        ingAdapter = new IngredientAdapter(ingredientList);
        ingredientRecyclerView.setAdapter(ingAdapter);

        // Initialize the presenter
        presenter = new SearchOptionsPresenter(this, new MealsRemoteDataSourceImp(RandomMealClient.getMealService()));
        presenter.fetchAllCategories();
        presenter.fetchCountries();
        presenter.fetchIngredients();

    }

    @Override
    public void displayCategories(List<Category> categories) {
        categoryList.clear();
        categoryList.addAll(categories);
        catAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayCountries(List<Meal> countries){
        countryList.clear();
        countryList.addAll(countries);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayIngredients(List<Meal> ingredients){
        ingredientList.clear();
        ingredientList.addAll(ingredients);
        ingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

}
