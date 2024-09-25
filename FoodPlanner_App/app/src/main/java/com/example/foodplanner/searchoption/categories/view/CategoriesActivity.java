package com.example.foodplanner.searchoption.categories.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.presenter.MealOfTheDayPresenter;
import com.example.foodplanner.mealofday.apphome.view.MealOfTheDayAdapter;
import com.example.foodplanner.searchoption.categories.model.Category;
import com.example.foodplanner.searchoption.categories.presenter.CategoryPresenter;
import com.example.foodplanner.network.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriesActivity extends AppCompatActivity implements CategoriesView{
    private RecyclerView recyclerView;
    private CategoryPresenter presenter;
    private List<Category> categoryList;
    private CategoryAdapter catAdapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        recyclerView = findViewById(R.id.categories_list_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        categoryList = new ArrayList<>();
        catAdapter = new CategoryAdapter(categoryList);
        recyclerView.setAdapter(catAdapter);

        presenter = new CategoryPresenter(this, new MealsRemoteDataSourceImp(RandomMealClient.getMealService()));

        presenter.fetchallCategories();

    }

    @Override
    public void displayCategories(List<Category> categories) {
        categoryList.addAll(categories);
        catAdapter.notifyDataSetChanged();
    }

    public void showError(String message){
        //hzbtha b3den
    }

}
