package com.example.foodplanner.searchoption.countries.presenter;

import com.example.foodplanner.network.MealsRemoteDataSource;
import com.example.foodplanner.network.NetworkCallBack;

import java.util.List;

public class CountriesPresenter {
    private final CountriesView view;
    private final MealsRemoteDataSource dataSource;

    public CountriesPresenter(CountriesView view, MealsRemoteDataSource dataSource ){
        this.view = view;
        this.dataSource = dataSource;
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
}
