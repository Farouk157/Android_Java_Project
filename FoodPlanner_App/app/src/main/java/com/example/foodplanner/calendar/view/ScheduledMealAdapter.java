package com.example.foodplanner.calendar.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Plan;

import java.util.ArrayList;
import java.util.List;

public class ScheduledMealAdapter extends RecyclerView.Adapter<ScheduledMealAdapter.MealViewHolder> {
    private final Context context;
    private List<Plan> planList;
    private CalendarOnRemoveClick listener;
    public ScheduledMealAdapter(Context context,List<Plan> planList,  CalendarOnRemoveClick listener) {
        this.context = context;
        this.planList = (planList != null) ? planList : new ArrayList<>();
        this.listener = listener;
    }

    public void setOnRemoveClickListener(CalendarOnRemoveClick listener) {
        this.listener = listener;
    }

    public void setPlans(List<Plan> plans) {
        this.planList = (plans != null) ? plans : new ArrayList<>();
    }
    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_scheduled_meal, parent, false);
        ScheduledMealAdapter.MealViewHolder vh = new ScheduledMealAdapter.MealViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Plan currentPlan = planList.get(position);
        holder.mealNameTextView.setText(planList.get(position).getMeal().getStrMeal());
        holder.mealDateTextView.setText(planList.get(position).getScheduledDate());
        Glide.with(context).load(planList.get(position).getMeal().getStrMealThumb()).apply(new RequestOptions().override(150, 200)
                )
                .into(holder.mealImageView);

        holder.removeMealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onRemoveMealClick(currentPlan);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (planList != null) ? planList.size() : 0;
    }

    static class MealViewHolder extends RecyclerView.ViewHolder {

        private ImageView mealImageView;
        private TextView mealNameTextView;
        private TextView mealDateTextView;
        private CardView cardView;
        private Button removeMealButton;
        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImageView = itemView.findViewById(R.id.mealImage);
            mealNameTextView = itemView.findViewById(R.id.mealName);
            mealDateTextView = itemView.findViewById(R.id.mealDate);
            removeMealButton = itemView.findViewById(R.id.removeButton);

        }

    }
}
