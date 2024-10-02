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
import com.example.foodplanner.search.model.Category;

import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> categories;
    private CategoryClickListener listener;

    public interface CategoryClickListener {
        void onCategoryClick(String categoryId);
    }

    public CategoryAdapter(List<Category> categories, CategoryClickListener listener) {
        this.categories = categories;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = categories.get(position);
        holder.categoryName.setText(category.getStrCategory());
        Glide.with(holder.itemView.getContext())
                .load(category.getStrCategoryThumb())
                .apply(RequestOptions.circleCropTransform())
                .into(holder.categoryImage);

        holder.itemView.setOnClickListener(v -> {
            Log.d("CategoryAdapter", "Category clicked: " + category.getStrCategory());
            listener.onCategoryClick(category.getStrCategory());
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        ImageView categoryImage;

        CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.title_category);
            categoryImage = itemView.findViewById(R.id.image_category);
        }
    }
}
