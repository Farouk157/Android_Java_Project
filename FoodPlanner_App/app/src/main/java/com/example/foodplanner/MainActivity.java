package com.example.foodplanner;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;

import com.example.foodplanner.mealofday.view.MealOfTheDayFragment;
import com.example.foodplanner.search.view.SearchOptionsFragment;
import com.example.foodplanner.favoritemeal.view.FavoriteMealsFragment;
import com.example.foodplanner.calendar.view.RetreiveTypeMeals;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private Button getStartedButton;
    private FragmentContainerView fragmentContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Initialize views
        navigationView = findViewById(R.id.bottom_navigation);
        getStartedButton = findViewById(R.id.get_started_button);
        fragmentContainer = findViewById(R.id.fragment_container);

        // Set default selected item for the BottomNavigationView
        navigationView.setSelectedItemId(R.id.nav_home);

        // Set the initial fragment
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MealOfTheDayFragment()).commit();

        // Set up navigation item click listeners
        setupNavigation();

        // Initially hide the BottomNavigationView
        navigationView.setVisibility(View.GONE);
        fragmentContainer.setVisibility(View.GONE); // Also hide the fragment container

        // Set the button click listener
        getStartedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Hide the Lottie animation and TextView
                findViewById(R.id.lottie_animation_view).setVisibility(View.GONE);
                findViewById(R.id.app_title).setVisibility(View.GONE);
                findViewById(R.id.app_slogan).setVisibility(View.GONE);
                getStartedButton.setVisibility(View.GONE); // Hide the button

                // Show the BottomNavigationView and fragment container
                navigationView.setVisibility(View.VISIBLE);
                fragmentContainer.setVisibility(View.VISIBLE);
            }
        });
    }

    private void setupNavigation() {
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();

                Fragment fragment = null;
                if (id == R.id.nav_home) {
                    // Handle home navigation here
                    fragment = new MealOfTheDayFragment();
                } else if (id == R.id.nav_search) {
                    // Handle search navigation
                    fragment = new SearchOptionsFragment();
                } else if (id == R.id.nav_favorite) {
                    // Handle favorite navigation
                    fragment = new FavoriteMealsFragment();
                } else if (id == R.id.nav_calender) {
                    fragment = new RetreiveTypeMeals();
                }

                // Replace the current fragment
                if (fragment != null) {
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