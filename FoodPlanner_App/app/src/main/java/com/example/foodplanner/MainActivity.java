package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.foodplanner.mealofday.apphome.view.MealOfTheDayFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.foodplanner.mealofday.search.view.SearchOptionsFragment;

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
                    return true;
                } else if (id == R.id.nav_calender) {
                    // Handle calendar navigation
                    return true;
                }
                return false;
            }
        });
    }
}
