package com.example.foodplanner.search.view;

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
import com.example.foodplanner.mealsbyingredient.view.*;
import com.example.foodplanner.R;
import com.example.foodplanner.mealsbycategory.view.MealsByCategoryFragment;
import com.example.foodplanner.mealofday.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.RandomMealClient;
import com.example.foodplanner.search.model.Category;
import com.example.foodplanner.search.presenter.SearchOptionsPresenter;
import com.example.foodplanner.mealsbycountry.view.*;
import com.example.foodplanner.database.*;
import java.util.ArrayList;
import java.util.List;
import com.example.foodplanner.mealofday.model.*;


public class SearchOptionsFragment extends Fragment implements SearchOptionsView, CategoryAdapter.CategoryClickListener, CountryAdapter.CountryClickListener, IngredientAdapter.IngredientClickListener{

    private SearchOptionsPresenter presenter_1;
    private SearchOptionsPresenter presenter_2;
    private SearchOptionsPresenter presenter_3;

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

        // Initialize RecyclerViews
        catRecyclerView = view.findViewById(R.id.rv_categories);
        catRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        categoryList = new ArrayList<>();
        catAdapter = new CategoryAdapter(categoryList, this);
        catRecyclerView.setAdapter(catAdapter);

        countryRecyclerView = view.findViewById(R.id.rv_countries);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        countryList = new ArrayList<>();
        countryAdapter = new CountryAdapter(countryList, this);
        countryRecyclerView.setAdapter(countryAdapter);

        ingredientRecyclerView = view.findViewById(R.id.rv_ingredients);
        ingredientRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3, GridLayoutManager.HORIZONTAL, false));
        ingredientList = new ArrayList<>();
        ingAdapter = new IngredientAdapter(ingredientList, this);
        ingredientRecyclerView.setAdapter(ingAdapter);

        // Initialize the presenter
        presenter_1 = new SearchOptionsPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()),MealsRemoteDataSourceImp.getInstance()));
        presenter_1.getCategories();
        presenter_2 = new SearchOptionsPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()),MealsRemoteDataSourceImp.getInstance()));
        presenter_2.getCountries();
        presenter_3 = new SearchOptionsPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()),MealsRemoteDataSourceImp.getInstance()));
        presenter_3.getIngredients();
    }

    @Override
    public void displayCategories(List<Category> categories) {
        categoryList.clear();
        categoryList.addAll(categories);
        catAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayCountries(List<Meal> countries) {
        countryList.clear();
        countryList.addAll(countries);
        countryAdapter.notifyDataSetChanged();
    }

    @Override
    public void displayIngredients(List<Meal> ingredients) {
        ingredientList.clear();
        ingredientList.addAll(ingredients);
        ingAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCategoryClick(String categoryId) {
        MealsByCategoryFragment mealsByCategoryFragment = new MealsByCategoryFragment();

        // Create a bundle to pass the category ID
        Bundle bundle = new Bundle();
        bundle.putString("CATEGORY_ID", categoryId);
        mealsByCategoryFragment.setArguments(bundle);

        // Replace the current fragment with CategoryFragment
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mealsByCategoryFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCountryClick(String countryId) {
        MealsByCountryFragment mealsByCountryFragment = new MealsByCountryFragment();

        // Create a bundle to pass the country ID
        Bundle bundle = new Bundle();
        bundle.putString("COUNTRY_ID", countryId);
        mealsByCountryFragment.setArguments(bundle);

        // Replace the current fragment with CategoryFragment
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mealsByCountryFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onIngredientClick(String ingredientId) {
        MealsByIngredientFragment mealsByIngredientFragment = new MealsByIngredientFragment();

        // Create a bundle to pass the country ID
        Bundle bundle = new Bundle();
        bundle.putString("INGREDIENT_ID", ingredientId);
        mealsByIngredientFragment.setArguments(bundle);

        // Replace the current fragment with CategoryFragment
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, mealsByIngredientFragment)
                .addToBackStack(null)
                .commit();
    }
}
