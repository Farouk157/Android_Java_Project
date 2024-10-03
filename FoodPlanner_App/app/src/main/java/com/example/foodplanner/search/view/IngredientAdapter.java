package com.example.foodplanner.search.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;

import java.util.List;
import com.example.foodplanner.mealofday.model.Meal;
public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder> {

    private List<Meal> ingredients;
    private IngredientAdapter.IngredientClickListener listener;

    public interface IngredientClickListener {
        void onIngredientClick(String ingredientId);
    }
    public IngredientAdapter(List<Meal> ingredients, IngredientAdapter.IngredientClickListener listener) {
        this.ingredients = ingredients;
        this.listener = listener;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_ingredient, parent, false);
        return new IngredientViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Meal ingredient = ingredients.get(position);
        if (ingredients != null && !ingredients.isEmpty()) {
            if (ingredient != null) {
                holder.ingredientName.setText(ingredient.getStrIngredient());
                Glide.with(holder.itemView.getContext())
                        .load(ingredient.getIngredientUrl())
                        .apply(RequestOptions.circleCropTransform()) // Apply circular crop
                        .into(holder.ingredientImage);
            } else {
                Log.e("CategoryAdapter", "Category object is null at position: " + position);
            }
        } else {
            Log.e("CategoryAdapter", "Categories list is null or empty");
        }
        holder.itemView.setOnClickListener(v -> {
            Log.d("IngredientAdapter", "Ingredient clicked: " + ingredient.getStrIngredient());
            listener.onIngredientClick(ingredient.getStrIngredient());
        });
    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    static class IngredientViewHolder extends RecyclerView.ViewHolder {
        TextView ingredientName;
        ImageView ingredientImage;

        IngredientViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientName = itemView.findViewById(R.id.title_ingredient);
            ingredientImage = itemView.findViewById(R.id.image_ingredient);
        }
    }
}
