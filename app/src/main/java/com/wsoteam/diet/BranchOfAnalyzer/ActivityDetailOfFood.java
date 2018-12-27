package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.wsoteam.diet.POJOFoodItem.ListOfFoodItem;
import com.wsoteam.diet.R;

public class ActivityDetailOfFood extends AppCompatActivity {
    private ImageView ivFoodIcon;
    private TextView tvTitle, tvKcal;
    private DonutProgress pbCarbohydrates;

    private ListOfFoodItem foodItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_food);

        foodItem = (ListOfFoodItem) getIntent().getSerializableExtra("ActivityDetailOfFood");
        ivFoodIcon = findViewById(R.id.ivActivityDetailOfFoodCollapsingBackGround);
        tvTitle = findViewById(R.id.tvActivityDetailOfFoodCollapsingTitle);
        tvKcal = findViewById(R.id.tvActivityDetailOfFoodKcal);
        pbCarbohydrates = findViewById(R.id.pbActivityDetailOfFoodCarbo);

        Glide.with(this).load(foodItem.getUrlOfImages()).into(ivFoodIcon);
        tvTitle.setText(foodItem.getName());
        tvKcal.setText(foodItem.getCalories() + " " + getString(R.string.for_100_g));
        pbCarbohydrates.setDonut_progress("90");


    }
}
