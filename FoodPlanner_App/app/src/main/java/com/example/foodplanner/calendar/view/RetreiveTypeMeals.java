package com.example.foodplanner.calendar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.foodplanner.R;


public class RetreiveTypeMeals extends Fragment {

    private Button breakfastButton;
    private Button lunchButton;
    private Button dinnerButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_retreive_type_meals, container, false);

        // Find the buttons in the layout
        breakfastButton = view.findViewById(R.id.scheduleBreakfastButton);
        lunchButton = view.findViewById(R.id.scheduleLunchButton);
        dinnerButton = view.findViewById(R.id.scheduleDinnerButton);

        // Set up click listeners for the buttons
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetreiveScheduledMealsFragment retreiveScheduledMealsFragment = new RetreiveScheduledMealsFragment();
                Bundle args = new Bundle();
                args.putString("type", "Breakfast");
                retreiveScheduledMealsFragment.setArguments(args);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, retreiveScheduledMealsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetreiveScheduledMealsFragment retreiveScheduledMealsFragment = new RetreiveScheduledMealsFragment();
                Bundle args = new Bundle();
                args.putString("type", "Lunch");
                retreiveScheduledMealsFragment.setArguments(args);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, retreiveScheduledMealsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetreiveScheduledMealsFragment retreiveScheduledMealsFragment = new RetreiveScheduledMealsFragment();
                Bundle args = new Bundle();
                args.putString("type", "Dinner");
                retreiveScheduledMealsFragment.setArguments(args);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, retreiveScheduledMealsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }
}
