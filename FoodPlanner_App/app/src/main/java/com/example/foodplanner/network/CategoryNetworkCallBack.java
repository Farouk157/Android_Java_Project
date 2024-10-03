package com.example.foodplanner.network;

import com.example.foodplanner.search.model.Category;

import java.util.List;

public interface CategoryNetworkCallBack {
    void onCategoryCallBackSuccess(List<Category> categoryList);
    void onCategoryCallBackFailure(String errorMessage);
}
