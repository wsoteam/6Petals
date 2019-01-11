package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wsoteam.diet.POJOFoodItem.FoodItem;
import com.wsoteam.diet.R;

public class ActivityRequestOfWatchADVideo extends AppCompatActivity {
    private TextView tvTitleOfRequestAd, tvPropertiesOfGroupRequestAdWatch, tvCountInGroup;
    private FoodItem foodItem;
    private FloatingActionButton fabMainImageAdRequest, fabCloseRequestAd;
    private CardView cvWatchAd;
    private LinearLayout llContainerWithImageAndTextAdButton;

    private Animation animationMovingFromBottom, animationChangeAlpha;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_of_watch_advideo);
        animationMovingFromBottom = AnimationUtils.loadAnimation(this, R.anim.moving_from_bottom);
        animationChangeAlpha = new AlphaAnimation(0, 1);
        animationChangeAlpha.setInterpolator(new DecelerateInterpolator());
        animationChangeAlpha.setStartOffset(1400);
        animationChangeAlpha.setDuration(400);

        foodItem = (FoodItem) getIntent().getSerializableExtra("ActivityRequestOfWatchADVideo");

        tvTitleOfRequestAd = findViewById(R.id.tvTitleOfRequestAd);
        tvPropertiesOfGroupRequestAdWatch = findViewById(R.id.tvPropertiesOfGroupRequestAdWatch);
        tvCountInGroup = findViewById(R.id.tvCountInGroup);
        fabCloseRequestAd = findViewById(R.id.fabCloseRequestAd);
        cvWatchAd = findViewById(R.id.cvRequestAdWatchButton);
        llContainerWithImageAndTextAdButton = findViewById(R.id.llContainerWithImageAndTextAdButton);

        cvWatchAd.setVisibility(View.GONE);
        llContainerWithImageAndTextAdButton.setVisibility(View.GONE);

        tvTitleOfRequestAd.setText(foodItem.getNameOfGroup());
        tvCountInGroup.setText("Продуктов - " + String.valueOf(foodItem.getCountOfItemsInGroup()) + " шт.");
        tvPropertiesOfGroupRequestAdWatch.setText("Мы можете открыть доступ к этой группе посмотрев всего лишь один короткий рекламный ролик. Открыть его можно кнопкой снизу.");

        cvWatchAd.startAnimation(animationMovingFromBottom);
        cvWatchAd.setVisibility(View.VISIBLE);
        llContainerWithImageAndTextAdButton.startAnimation(animationChangeAlpha);
        llContainerWithImageAndTextAdButton.setVisibility(View.VISIBLE);

        fabCloseRequestAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
