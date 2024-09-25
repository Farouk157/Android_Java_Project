package com.example.foodplanner.searchoption.countries.view;

import com.example.foodplanner.searchoption.countries.model.Country;

import java.util.List;

public interface CountriesView {
    void displayCountries(List<Country> countries);
    void showError(String message);
}
