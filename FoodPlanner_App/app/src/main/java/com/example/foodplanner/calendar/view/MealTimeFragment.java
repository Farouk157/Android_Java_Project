package com.example.foodplanner.calendar.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.foodplanner.R;
import com.example.foodplanner.calendar.presenter.*;
import com.example.foodplanner.database.*;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.*;

public class MealTimeFragment extends Fragment {

    private Button breakfastButton;
    private Button lunchButton;
    private Button dinnerButton;
    private Meal meal;
    private String date;
    private MealTimePresenter mealTimePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Retreive the meal and also the data choosen by the user
        if(getArguments()!= null){
            meal = (Meal) getArguments().getSerializable("meal");
            date = getArguments().getString("selected_date");
        }

        mealTimePresenter = new MealTimePresenter(getContext(), Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()), MealsRemoteDataSourceImp.getInstance()));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal_time, container, false);

        // Find the buttons in the layout
        breakfastButton = view.findViewById(R.id.addBreakfastButton);
        lunchButton = view.findViewById(R.id.addLunchButton);
        dinnerButton = view.findViewById(R.id.addDinnerButton);

        // Set up click listeners for the buttons
        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for breakfast button
                mealTimePresenter.addMealToPlan(meal, date, "Breakfast");
                Toast.makeText(getActivity(), "Added to Breakfast", Toast.LENGTH_SHORT).show();
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for lunch button
                mealTimePresenter.addMealToPlan(meal, date, "Lunch");
                Toast.makeText(getActivity(), "Added to Lunch", Toast.LENGTH_SHORT).show();
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for dinner button
                mealTimePresenter.addMealToPlan(meal, date, "Dinner");
                Toast.makeText(getActivity(), "Added to Dinner", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
