package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.wsoteam.diet.POJOFoodItem.FoodItem;
import com.wsoteam.diet.R;

public class ActivityRequestOfWatchADVideo extends AppCompatActivity {
    private TextView tvTitleOfRequestAd, tvPropertiesOfGroupRequestAdWatch, tvCountInGroup;
    private FoodItem foodItem;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_of_watch_advideo);

        foodItem = (FoodItem) getIntent().getSerializableExtra("ActivityRequestOfWatchADVideo");

        tvTitleOfRequestAd = findViewById(R.id.tvTitleOfRequestAd);
        tvPropertiesOfGroupRequestAdWatch = findViewById(R.id.tvPropertiesOfGroupRequestAdWatch);
        tvCountInGroup = findViewById(R.id.tvCountInGroup);

        tvTitleOfRequestAd.setText(foodItem.getNameOfGroup());
        tvCountInGroup.setText("Количество продуктов - " + String.valueOf(foodItem.getCountOfItemsInGroup()) + " шт.");
        tvPropertiesOfGroupRequestAdWatch.setText("Мы можете открыть доступ к этой группе посмотрев всего лишь один короткий рекламный ролик. Открыть его можно кнопкой снизу.");



    }
}
