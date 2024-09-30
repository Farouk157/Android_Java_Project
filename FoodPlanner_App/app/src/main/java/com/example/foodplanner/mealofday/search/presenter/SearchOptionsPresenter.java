package com.example.foodplanner.mealofday.search.presenter;

import android.util.Log;

import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.mealofday.search.view.SearchOptionsView;
import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.searchoption.categories.model.Category;

import java.util.List;

public class SearchOptionsPresenter {

    private final SearchOptionsView view;
    private  final MealsRemoteDataSource dataSource;

    public SearchOptionsPresenter(SearchOptionsView view, MealsRemoteDataSource dataSource ){
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchAllCategories(){
        dataSource.fetchCategories(new NetworkCallBack<List<Category>>(){
            // view.showLoading() // roh zaweed el loading gif hena
            @Override
            public void onSuccess(List<Category> categories){
                // view.hideLoading(); // Hide loading indicator
                if(categories != null){
                    view.displayCategories(categories);
                }
                else{
                    Log.e("CategoryPresenter", "categories is null or empty");
                }
            }

            @Override
            public void onFailure(String errMsg) {
//                view.hideLoading(); //roh hot hena hide GIF
                view.showError(errMsg);
            }
        });
    }

    public void fetchCountries(){
        dataSource.fetchCountries(new NetworkCallBack<List<Meal>>(){
            // view.showLoading() // roh zaweed el loading gif hena
            @Override
            public void onSuccess(List<Meal> countries){
                // view.hideLoading(); // Hide loading indicator
                view.displayCountries(countries);
            }

            @Override
            public void onFailure(String errMsg) {
//                view.hideLoading(); //roh hot hena hide GIF
                view.showError(errMsg);
            }
        });
    }

    public void fetchIngredients(){
        dataSource.fetchIngredients(new NetworkCallBack<List<Meal>>(){
            // view.showLoading() // roh zaweed el loading gif hena
            @Override
            public void onSuccess(List<Meal> ingredients){
                // view.hideLoading(); // Hide loading indicator
                view.displayIngredients(ingredients);
            }

            @Override
            public void onFailure(String errMsg) {
//                view.hideLoading(); //roh hot hena hide GIF
                view.showError(errMsg);
            }
        });
    }

}
