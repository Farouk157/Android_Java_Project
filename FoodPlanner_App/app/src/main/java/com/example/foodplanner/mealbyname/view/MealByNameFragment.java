package com.example.foodplanner.mealbyname.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealdetails.view.MealDetailsFragment;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.mealbyname.presenter.*;
import com.example.foodplanner.model.*;
import com.example.foodplanner.network.*;
import com.example.foodplanner.database.*;

public class MealByNameFragment extends Fragment implements MealByNameView{

    private MealByNamePresenter presenter;
    private TextView mealNameTextView;
    private TextView mealCountryTextView;
    private ImageView mealImageView;
    private Meal meal;
    private View cardMealDetails;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new MealByNamePresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()), MealsRemoteDataSourceImp.getInstance()));

        String mealName = getArguments().getString("MEAL_NAME");
        if (mealName != null) {
            presenter.fetchMealByName(mealName);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_meal_by_name, container, false);
        mealNameTextView =view.findViewById(R.id.meal_name);
        mealCountryTextView = view.findViewById(R.id.meal_country);
        mealImageView = view.findViewById(R.id.meal_image);
        cardMealDetails = view.findViewById(R.id.card_meal_details);


        cardMealDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click event
                MealDetailsFragment mealDetailsFragment = MealDetailsFragment.newInstance(meal);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mealDetailsFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;
    }

    @Override
    public void displayMeal(Meal meal) {
        this.meal = meal;
        mealNameTextView.setText(meal.getStrMeal());
        mealCountryTextView.setText(meal.getStrArea());
        Glide.with(this)
                .load(meal.getStrMealThumb())
                .into(mealImageView);

    }

    @Override
    public void showError(String errorMessage) {
        mealNameTextView.setText("Error: " + errorMessage);
    }
}