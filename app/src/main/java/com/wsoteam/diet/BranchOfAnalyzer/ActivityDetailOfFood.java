package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.wsoteam.diet.POJOFoodItem.ListOfFoodItem;
import com.wsoteam.diet.R;

public class ActivityDetailOfFood extends AppCompatActivity {
    private TextView tvTitle, tvKcal, tvProtein, tvFat, tvCarbohydrates;
    private DonutProgress pbCarbohydrates, pbFat, pbProtein;

    private ListOfFoodItem foodItem;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_of_food);

        foodItem = (ListOfFoodItem) getIntent().getSerializableExtra("ActivityDetailOfFood");
        tvTitle = findViewById(R.id.tvActivityDetailOfFoodCollapsingTitle);
        tvKcal = findViewById(R.id.tvActivityDetailOfFoodKcal);
        tvCarbohydrates = findViewById(R.id.tvActivityDetailOfFoodCountOfCarbohydrates);
        tvFat = findViewById(R.id.tvActivityDetailOfFoodCountOfFat);
        tvProtein = findViewById(R.id.tvActivityDetailOfFoodCountOfProtein);
        pbCarbohydrates = findViewById(R.id.pbActivityDetailOfFoodCarbo);
        pbFat = findViewById(R.id.pbActivityDetailOfFoodFat);
        pbProtein = findViewById(R.id.pbActivityDetailOfFoodProtein);


        tvTitle.setText(foodItem.getName());
        tvKcal.setText(foodItem.getCalories() + " " + getString(R.string.for_100_g));
        tvFat.setText(getString(R.string.fat_detail) + " " + foodItem.getFat() + "" + getString(R.string.gramm));
        tvProtein.setText(getString(R.string.protein_detail) + " " + foodItem.getProtein() + " " + getString(R.string.gramm));
        tvCarbohydrates.setText(getString(R.string.carbohydrates_detail) + " " + foodItem.getCarbohydrates() + " " + getString(R.string.gramm));

        calculateNumbersForProgressBars();


    }

    private void calculateNumbersForProgressBars() {
        Double fat, carbohydrates, protein;

        fat = Double.parseDouble(foodItem.getFat());
        carbohydrates = Double.parseDouble(foodItem.getCarbohydrates());
        protein = Double.parseDouble(foodItem.getProtein());

        pbFat.setDonut_progress(String.valueOf(fat.intValue()));
        pbProtein.setDonut_progress(String.valueOf(protein.intValue()));
        pbCarbohydrates.setDonut_progress(String.valueOf(carbohydrates.intValue()));
    }
}
