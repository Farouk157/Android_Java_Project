package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.mealofday.view.MealOfTheDayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.foodplanner.search.view.SearchOptionsFragment;
import com.example.foodplanner.favoritemeal.view.*;
import com.example.foodplanner.calendar.view.*;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize BottomNavigationView before using it
        navigationView = findViewById(R.id.bottom_navigation);

        // Set default selected item
        navigationView.setSelectedItemId(R.id.nav_home);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MealOfTheDayFragment()).commit();

        // Set up navigation item click listeners
        setupNavigation();
    }

    private void setupNavigation() {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                if (id == R.id.nav_home) {
                    // Handle home navigation here
                    Fragment fragment = new MealOfTheDayFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                } else if (id == R.id.nav_search) {
                    // Handle search navigation
                    Fragment fragment = new SearchOptionsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                } else if (id == R.id.nav_favorite) {
                    // Handle favorite navigation
                    Fragment fragment = new FavoriteMealsFragment();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                } else if (id == R.id.nav_calender) {
                    Fragment fragment = new RetreiveTypeMeals();
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public void onBackPressed() {
        // Check if the fragment manager has any back stack entries
        if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
            // If there are entries in the back stack, pop the back stack
            getSupportFragmentManager().popBackStack();
        } else {
            // If there are no entries, let the system handle the back press (close app)
            super.onBackPressed();
        }
    }
}
