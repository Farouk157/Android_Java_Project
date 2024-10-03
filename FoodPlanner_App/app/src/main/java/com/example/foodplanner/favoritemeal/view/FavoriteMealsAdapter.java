package com.example.foodplanner.favoritemeal.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.mealofday.model.*;
import java.util.List;


public class FavoriteMealsAdapter extends RecyclerView.Adapter<FavoriteMealsAdapter.ViewHolder> {
    private final Context context;
    private List<Meal> meals;
    private FavoriteMealsOnClick favoriteMealOnClick;


    public FavoriteMealsAdapter(Context context, List<Meal> meals, FavoriteMealsOnClick favoriteMealOnClick) {
        this.context = context;
        this.meals = meals;
        this.favoriteMealOnClick = favoriteMealOnClick;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_fav_item_meal, parent, false);
        FavoriteMealsAdapter.ViewHolder vh = new FavoriteMealsAdapter.ViewHolder(view);
        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteMealOnClick.onMealClick(meals.get(position));
            }
        });
        holder.favMealTextView.setText(meals.get(position).getStrMeal());
        holder.removeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteMealOnClick.onRemoveMealClick(meals.get(position));
            }
        });
        Glide.with(context).load(meals.get(position).getStrMealThumb()).apply(new RequestOptions().override(150, 200)
                )
                .into(holder.favImageView);
        holder.favMealCategoryTextView.setText(meals.get(position).getStrCategory());
        holder.favMealCountryTextView.setText(meals.get(position).getStrArea());
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView favImageView;
        Button removeBtn;
        TextView favMealTextView;
        TextView favMealCategoryTextView;
        TextView favMealCountryTextView;
        CardView cardView;

        public ViewHolder(@NonNull View view) {
            super(view);
            favImageView = view.findViewById(R.id.iv_fav_meal_image);
            removeBtn = view.findViewById(R.id.btn_remove);
            favMealTextView = view.findViewById(R.id.tv_fav_meal_name);
            favMealCategoryTextView = view.findViewById(R.id.tv_fav_meal_category);
            favMealCountryTextView = view.findViewById(R.id.tv_fav_meal_country);
            cardView = view.findViewById(R.id.card_fav);
        }
    }
}


