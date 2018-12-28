package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.lzyzsd.circleprogress.DonutProgress;
import com.wsoteam.diet.POJOFoodItem.ListOfFoodItem;
import com.wsoteam.diet.R;

public class ActivityDetailOfFood extends AppCompatActivity {
    private TextView tvTitle, tvKcal,
            tvProtein, tvFat, tvCarbohydrates,
            tvCalculateFat, tvCalculateProtein, tvCalculateCarbohydrates, tvCalculateKcal;
    private DonutProgress pbCarbohydrates, pbFat, pbProtein;
    private EditText edtWeight;

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

        tvCalculateFat = findViewById(R.id.tvActivityDetailOfFoodCalculateFat);
        tvCalculateCarbohydrates = findViewById(R.id.tvActivityDetailOfFoodCalculateCarbo);
        tvCalculateKcal = findViewById(R.id.tvActivityDetailOfFoodCalculateKcal);
        tvCalculateProtein = findViewById(R.id.tvActivityDetailOfFoodCalculateProtein);

        edtWeight = findViewById(R.id.edtActivityDetailOfFoodPortion);

        pbCarbohydrates = findViewById(R.id.pbActivityDetailOfFoodCarbo);
        pbFat = findViewById(R.id.pbActivityDetailOfFoodFat);
        pbProtein = findViewById(R.id.pbActivityDetailOfFoodProtein);


        tvTitle.setText(foodItem.getName());
        tvKcal.setText(foodItem.getCalories() + " " + getString(R.string.for_100_g));
        tvFat.setText(getString(R.string.fat_detail) + " " + foodItem.getFat() + "" + getString(R.string.gramm));
        tvProtein.setText(getString(R.string.protein_detail) + " " + foodItem.getProtein() + " " + getString(R.string.gramm));
        tvCarbohydrates.setText(getString(R.string.carbohydrates_detail) + " " + foodItem.getCarbohydrates() + " " + getString(R.string.gramm));

        calculateNumbersForProgressBars();

        edtWeight.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().equals(" ")
                        || charSequence.toString().equals("-")) {
                    edtWeight.setText("0");
                } else {
                    if (!edtWeight.getText().toString().equals("")) {
                        calculateMainParameters();
                    } else {
                        tvCalculateProtein.setText("0 " + getString(R.string.gramm));
                        tvCalculateKcal.setText("0 " + getString(R.string.kcal));
                        tvCalculateCarbohydrates.setText("0 " + getString(R.string.gramm));
                        tvCalculateFat.setText("0 " + getString(R.string.gramm));
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


    }

    private void calculateMainParameters() {
        Double fat, carbohydrates, protein, kcal, partOfStartWeight;

        fat = Double.parseDouble(foodItem.getFat());
        carbohydrates = Double.parseDouble(foodItem.getCarbohydrates());
        protein = Double.parseDouble(foodItem.getProtein());
        kcal = Double.parseDouble(foodItem.getCalories());

        partOfStartWeight = Double.parseDouble(edtWeight.getText().toString()) / 100;

        fat = fat * partOfStartWeight;
        carbohydrates = carbohydrates * partOfStartWeight;
        protein = protein * partOfStartWeight;
        kcal = kcal * partOfStartWeight;

        tvCalculateProtein.setText(String.valueOf(protein.intValue()) + " " + getString(R.string.gramm));
        tvCalculateKcal.setText(String.valueOf(kcal.intValue()) + " " + getString(R.string.kcal));
        tvCalculateCarbohydrates.setText(String.valueOf(carbohydrates.intValue()) + " " + getString(R.string.gramm));
        tvCalculateFat.setText(String.valueOf(fat.intValue()) + " " + getString(R.string.gramm));

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
