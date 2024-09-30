package com.example.foodplanner.searchoption.countries.view;

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
import com.example.foodplanner.searchoption.categories.model.Category;

import java.util.List;
import com.example.foodplanner.mealofday.apphome.model.*;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<Meal> countries;

    public CountryAdapter(List<Meal> countries) {
        this.countries = countries;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        if (countries != null && !countries.isEmpty()) {
            Meal country = countries.get(position);
            if (country != null) {
                holder.countryName.setText(country.getStrArea());

                // Load the flag using the URL from the Meal model's getFlagUrl method
                Glide.with(holder.itemView.getContext())
                        .load(country.getFlagUrl())
                        .apply(RequestOptions.circleCropTransform()) // Apply circular crop for better UI
                        .into(holder.countryImage);
            } else {
                Log.e("CountryAdapter", "Country object is null at position: " + position);
            }
        } else {
            Log.e("CountryAdapter", "Country list is null or empty");
        }
    }


    @Override
    public int getItemCount() {
        return countries.size();
    }

    static class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView countryName;
        ImageView countryImage;

        CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            countryName = itemView.findViewById(R.id.title_country);
            countryImage = itemView.findViewById(R.id.image_country);
        }
    }
}
