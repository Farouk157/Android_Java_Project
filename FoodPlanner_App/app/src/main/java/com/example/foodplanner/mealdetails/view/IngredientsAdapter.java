package com.example.foodplanner.mealdetails.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealdetails.model.*;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.ViewHolder> {
    private List<Ingredient> ingredients;
    private Context context;

    public IngredientsAdapter(Context context, List<Ingredient> ingredients) {
        this.ingredients = ingredients;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ingredient_details, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Ingredient ingredient = ingredients.get(position);
        holder.ingredientNameTextView.setText(ingredient.getName());
        holder.measureTextView.setText(ingredient.getMeasure());

        // Load the ingredient image (you can use libraries like Picasso or Glide)
        Glide.with(holder.itemView.getContext())
                .load("https://www.themealdb.com/images/ingredients/"+holder.ingredientNameTextView.getText()+".png")
                .into(holder.ingredientImageView);
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientNameTextView;
        TextView measureTextView;
        ImageView ingredientImageView;

        public ViewHolder(View itemView) {
            super(itemView);
            ingredientNameTextView = itemView.findViewById(R.id.ingredient_name);
            measureTextView = itemView.findViewById(R.id.ingredient_number);
            ingredientImageView = itemView.findViewById(R.id.ingredient_image);
        }
    }
}
