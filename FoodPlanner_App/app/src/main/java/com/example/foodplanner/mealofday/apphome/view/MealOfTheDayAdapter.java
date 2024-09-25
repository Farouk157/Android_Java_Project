package com.example.foodplanner.mealofday.apphome.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealofday.apphome.model.Meal;
import com.example.foodplanner.mealofday.mealdetails.view.MealDetailsFragment;

import java.util.List;

public class MealOfTheDayAdapter extends RecyclerView.Adapter<MealOfTheDayAdapter.MealViewHolder> {

    private List<Meal> mealList;
    private FragmentActivity activity;
    private OnMealClickListener listener;

    // Define the interface for meal click events
    public interface OnMealClickListener {
        void onMealClick(Meal meal);
    }

    public MealOfTheDayAdapter(List<Meal> mealList, FragmentActivity activity, OnMealClickListener listener) { // Modify constructor
        this.mealList = mealList;
        this.activity = activity;
        this.listener = listener; // Initialize listener
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_meal, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal meal = mealList.get(position);
        holder.tvMealName.setText(meal.getStrMeal());

        Glide.with(holder.itemView.getContext())
                .load(meal.getStrMealThumb())
                .into(holder.ivMealImage);

        holder.itemView.setOnClickListener(v -> {
            listener.onMealClick(meal);
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        TextView tvMealName;
        ImageView ivMealImage;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMealName = itemView.findViewById(R.id.tv_meal_name);
            ivMealImage = itemView.findViewById(R.id.iv_meal_image);
        }
    }
}
