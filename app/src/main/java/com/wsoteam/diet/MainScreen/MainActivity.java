package com.wsoteam.diet.MainScreen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.OtherActivity.ActivityEmpty;
import com.wsoteam.diet.BranchOfCalculating.ActivityListOfCalculating;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityMonoDiet;
import com.wsoteam.diet.BranchOfRecipes.ActivityRecipes;
import com.wsoteam.diet.R;

public class MainActivity extends AppCompatActivity {
    private CardView cvMonoDiets, cvCalculating, cvDiary, cvRecipes, cvAnalyzator;
    private ImageView ivDiets, ivCalculating, ivDiary, ivRecipes, ivAnalyzator;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cvMonoDiets = findViewById(R.id.cvMAMonoDiets);
        cvCalculating = findViewById(R.id.cvMACalculation);
        cvDiary = findViewById(R.id.cvMADiary);
        cvRecipes = findViewById(R.id.cvMARecipe);
        cvAnalyzator = findViewById(R.id.cvMAAnalyzer);

        ivAnalyzator = findViewById(R.id.ivAnalyzerMA);
        ivCalculating = findViewById(R.id.ivCalculationMA);
        ivDiary = findViewById(R.id.ivDiaryMA);
        ivDiets = findViewById(R.id.ivDietsMA);
        ivRecipes = findViewById(R.id.ivRecipesMA);

        Glide.with(this).load(R.drawable.background_diets).into(ivDiets);
        Glide.with(this).load(R.drawable.background_analyzer).into(ivAnalyzator);
        Glide.with(this).load(R.drawable.background_calculate).into(ivCalculating);
        Glide.with(this).load(R.drawable.background_diary).into(ivDiary);
        Glide.with(this).load(R.drawable.background_recipes).into(ivRecipes);


        cvCalculating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityListOfCalculating.class);
                startActivity(intent);
            }
        });
        cvMonoDiets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityMonoDiet.class);
                startActivity(intent);
            }
        });
        cvDiary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityEmpty.class);
                startActivity(intent);
            }
        });
        cvRecipes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityRecipes.class);
                startActivity(intent);
            }
        });
        cvAnalyzator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ActivityEmpty.class);
                startActivity(intent);
            }
        });


    }

}
