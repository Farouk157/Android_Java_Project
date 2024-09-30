package com.example.foodplanner.searchoption.categories.presenter;

import android.util.Log;

import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;
import com.example.foodplanner.searchoption.categories.model.Category;

import java.util.List;

public class CategoryPresenter {
    private final CategoriesView view;
    private  final MealsRemoteDataSource dataSource;

    public CategoryPresenter(CategoriesView view, MealsRemoteDataSource dataSource ){
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
}
