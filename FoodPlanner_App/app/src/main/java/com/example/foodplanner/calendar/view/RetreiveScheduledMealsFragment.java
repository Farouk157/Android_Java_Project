package com.example.foodplanner.calendar.view;

import android.os.Bundle;
import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.calendar.presenter.*;
import com.example.foodplanner.model.Plan;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.*;
import com.example.foodplanner.database.*;
import java.util.ArrayList;
import java.util.List;


public class RetreiveScheduledMealsFragment extends Fragment implements CalendarOnRemoveClick{
    private RecyclerView recyclerView;
    private ScheduledMealAdapter scheduledMealAdapter;
    private RetreiveScheduledMealsPresenter presenter;
    private String type;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new RetreiveScheduledMealsPresenter(getContext(),
                Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()),
                        MealsRemoteDataSourceImp.getInstance()));

        // Retrieve type
        if (getArguments() != null) {
            type = getArguments().getString("type");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_retreive_scheduled_meals, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewScheduledMeals);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        scheduledMealAdapter = new ScheduledMealAdapter(getContext(), null, this);
        recyclerView.setAdapter(scheduledMealAdapter);


        presenter.getScheduledMeals(type).observe(getViewLifecycleOwner(), new Observer<List<Plan>>() {
            @Override
            public void onChanged(List<Plan> meals) {
                if (meals != null) {
                    scheduledMealAdapter.setPlans(meals);
                } else {
                    scheduledMealAdapter.setPlans(new ArrayList<>()); // Set an empty list if meals are null
                }
                scheduledMealAdapter.notifyDataSetChanged();
            }
        });

        refreshScheduledMeals();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Handle back press to pop the fragment
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Pop the fragment from the back stack
                getParentFragmentManager().popBackStack();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    @Override
    public void onRemoveMealClick(Plan plan) {
        presenter.removePlan(plan);
        Toast.makeText(getContext(), "Meal removed successfully", Toast.LENGTH_SHORT).show();
        refreshScheduledMeals();
    }

    private void refreshScheduledMeals() {
        presenter.getScheduledMeals(type).observe(getViewLifecycleOwner(), new Observer<List<Plan>>() {
            @Override
            public void onChanged(List<Plan> meals) {
                if (meals != null) {
                    scheduledMealAdapter.setPlans(meals);
                } else {
                    scheduledMealAdapter.setPlans(new ArrayList<>()); // Set an empty list if meals are null
                }
                scheduledMealAdapter.notifyDataSetChanged();
            }
        });
    }
}
