package com.example.foodplanner.mealofday.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.mealofday.presenter.MealOfTheDayPresenter;
import com.example.foodplanner.mealdetails.view.MealDetailsFragment;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;
import com.example.foodplanner.database.*;
//ss


public class MealOfTheDayFragment extends Fragment implements MealOfTheDayView, MealOfTheDayAdapter.OnMealClickListener{
    private RecyclerView rvMeals;
    private MealOfTheDayAdapter mealAdapter;
    private MealOfTheDayPresenter presenter;
    private List<Meal> mealList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
           View layout = inflater.inflate(R.layout.fragment_home, container, false);
        return layout;
    }

    @Override
    public void onViewCreated(View view, Bundle saveInstanceState){
        super.onViewCreated(view, saveInstanceState);
        rvMeals = view.findViewById(R.id.rv_avail_meals);
        rvMeals.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mealList = new ArrayList<>();
        mealAdapter = new MealOfTheDayAdapter(mealList, this.getActivity(), this);
        rvMeals.setAdapter(mealAdapter);
        presenter = new MealOfTheDayPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()), MealsRemoteDataSourceImp.getInstance()));
        presenter.getMeals();
    }

    @Override
    public void displayMeals(List<Meal> meals) {
        mealList.clear();
        mealList.addAll(meals);
        mealAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        } else {
            Log.e("MealOfTheDayFragment", "Fragment not attached to an activity. Unable to show Toast.");
        }
    }

    @Override
    public void onMealClick(Meal meal) {
        MealDetailsFragment mealDetailsFragment = MealDetailsFragment.newInstance(meal);
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, mealDetailsFragment);

        if (getActivity() != null) {
            getActivity().findViewById(R.id.fragment_container).setVisibility(View.VISIBLE);
        }

        transaction.addToBackStack(null);
        transaction.commit();
    }

}