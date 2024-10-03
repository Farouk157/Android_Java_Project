package com.example.foodplanner.searchoption.ingredients.presenter;

import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;

import java.util.List;

public class IngredientsPresenter {
    private final IngredientsView view;
    private final  MealsRemoteDataSource dataSource;

    public IngredientsPresenter(IngredientsView view, MealsRemoteDataSource dataSource ){
        this.view = view;
        this.dataSource = dataSource;
    }

    public void fetchIngredients(){
        dataSource.fetchIngredients(new NetworkCallBack<List<Ingredient>>(){
            // view.showLoading() // roh zaweed el loading gif hena
            @Override
            public void onSuccess(List<Ingredient> ingredients){
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
