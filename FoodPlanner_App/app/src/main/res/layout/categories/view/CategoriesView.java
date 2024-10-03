package com.example.foodplanner.searchoption.categories.view;

import com.example.foodplanner.search.model.Category;

import java.util.List;

public interface CategoriesView {
    void displayCategories(List<Category> categories);
    void showError(String message);
    // zawed Hena showLoading
    // w kman zaweed hideLoading
}
