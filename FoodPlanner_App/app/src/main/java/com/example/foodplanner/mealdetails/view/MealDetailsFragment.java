package com.example.foodplanner.mealdetails.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodplanner.calendar.view.*;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.mealdetails.model.Ingredient;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.mealdetails.presenter.MealDetailsPresenter;
import com.example.foodplanner.model.Repository;
import com.example.foodplanner.network.*;
import java.util.ArrayList;
import java.util.List;
import com.example.foodplanner.database.*;

public class MealDetailsFragment extends Fragment implements MealDetailsView {

    private TextView tvMealName, tvOriginCountry, tvInstructions;
    private ImageView ivMealImage;
    private WebView webView;
    private MealDetailsPresenter presenter;
    private RecyclerView recyclerView;
    private List<Ingredient> ingredientList = new ArrayList<>();
    private ImageButton mealAddToFavBtn;
    private ImageButton mealAddToCalendarBtn;
    private Meal meal;

    public static MealDetailsFragment newInstance(Meal meal) {
        MealDetailsFragment fragment = new MealDetailsFragment();
        Bundle args = new Bundle();
        args.putSerializable("meal", meal);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal_details, container, false);

        // Initialize views
        tvMealName = view.findViewById(R.id.tv_fav_meal_name);
        ivMealImage = view.findViewById(R.id.iv_fav_meal_image);
        tvOriginCountry = view.findViewById(R.id.tv_origin_country);
        tvInstructions = view.findViewById(R.id.tv_instruction_content);
        webView = view.findViewById(R.id.wv_video);
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        mealAddToFavBtn = view.findViewById(R.id.ib_favorite);
        mealAddToCalendarBtn = view.findViewById(R.id.ib_calendar);

        recyclerView = view.findViewById(R.id.rv_ingredients_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Initialize presenter
        presenter = new MealDetailsPresenter(this, Repository.getInstance(MealLocalDataSourceImp.getInstance(getActivity()),MealsRemoteDataSourceImp.getInstance()));

        // Retrieve the meal object from arguments
        if (getArguments() != null) {
            meal = (Meal) getArguments().getSerializable("meal");
            presenter.displayMealDetails(meal);
        }

        mealAddToFavBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.addMeal(meal);
                Toast.makeText(getActivity(),meal.getStrMeal()+"added to Favourites",Toast.LENGTH_SHORT).show();
            }
        });

        mealAddToCalendarBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CalendarFragment calendarFragment = new CalendarFragment();
                Bundle args = new Bundle();
                args.putSerializable("meal", meal);
                calendarFragment.setArguments(args);
                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, calendarFragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });

        return view;
    }

    @Override
    public void showMealDetails(Meal meal) {
        // Populate UI with meal details
        tvMealName.setText(meal.getStrMeal() != null ? meal.getStrMeal() : "N/A");
        tvOriginCountry.setText(meal.getStrArea() != null ? meal.getStrArea() : "Unknown");
        tvInstructions.setText(meal.getStrInstructions() != null ? meal.getStrInstructions() : "No instructions available");

        // Load image using Glide, handling case if URL is null or empty
        String imageUrl = meal.getStrMealThumb();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(requireContext())
                    .load(imageUrl)
                    .into(ivMealImage);
        } else {
            ivMealImage.setImageResource(R.drawable.placeholder); // Use a placeholder if image is missing
        }

        // Load the YouTube video URL in the WebView
        String youtubeLink = meal.getStrYoutube(); // Make sure this field is populated in your Meal model
        if (youtubeLink != null && !youtubeLink.isEmpty()) {
            String videoId = youtubeLink.split("v=")[1];  // Extract video ID from the URL
            String embedUrl = "https://www.youtube.com/embed/" + videoId;
            webView.loadUrl(embedUrl);
        } else {
            // Optionally, show a message if there's no video link
            webView.loadData("<html><body><h3>No video available for this meal.</h3></body></html>", "text/html", "UTF-8");
        }

        for (int i = 1; i <= 20; i++) {
            String ingredientName = null;
            try {
                ingredientName = (String) meal.getClass().getDeclaredField("strIngredient" + i).get(meal);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            String ingredientMeasure = null;
            try {
                ingredientMeasure = (String) meal.getClass().getDeclaredField("strMeasure" + i).get(meal);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            if (ingredientName != null && !ingredientName.isEmpty()) {
                ingredientList.add(new Ingredient(ingredientName, ingredientMeasure));
            }
            IngredientsAdapter ingredientAdapter = new IngredientsAdapter(this.getContext(), ingredientList);
            recyclerView.setAdapter(ingredientAdapter);
        }
    }

    @Override
    public void showError(String message) {
        // Show error message in a Toast or a TextView
        tvMealName.setText(message); // Example: you can show an error message on the UI
    }

}
