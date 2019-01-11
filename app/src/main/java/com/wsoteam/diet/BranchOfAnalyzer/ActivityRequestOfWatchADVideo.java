package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.reward.RewardItem;
import com.google.android.gms.ads.reward.RewardedVideoAd;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import com.wsoteam.diet.OtherActivity.ActivityEmpty;
import com.wsoteam.diet.POJOFoodItem.FoodItem;
import com.wsoteam.diet.R;

public class ActivityRequestOfWatchADVideo extends AppCompatActivity {
    private TextView tvTitleOfRequestAd, tvPropertiesOfGroupRequestAdWatch, tvCountInGroup, tvToastCompleteGift;
    private FoodItem foodItem;
    private FloatingActionButton fabMainImageAdRequest, fabCloseRequestAd;
    private CardView cvWatchAd;
    private LinearLayout llContainerWithImageAndTextAdButton;
    private ImageView ivToastCompleteGift;

    private Animation animationMovingFromBottom, animationChangeAlpha;
    private RewardedVideoAd mRewardedVideoAd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_of_watch_advideo);

        loadAd();

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

        LayoutInflater toastInflater = getLayoutInflater();
        View toastLayout = toastInflater.inflate(R.layout.toast_complete_gift, null, false);
        tvToastCompleteGift = toastLayout.findViewById(R.id.tvToastCompleteGift);
        ivToastCompleteGift = toastLayout.findViewById(R.id.ivToastCompleteGift);
        tvToastCompleteGift.setText("Открыт доступ к - " + foodItem.getNameOfGroup());
        choiseIcon(foodItem.getNameOfGroup());
        Glide.with(this).load(R.drawable.ic_of_request_of_watch_advideo).into(ivToastCompleteGift);

        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(toastLayout);
        toast.show();

        mRewardedVideoAd.setRewardedVideoAdListener(new RewardedVideoAdListener() {
            @Override
            public void onRewardedVideoAdLoaded() {
                cvWatchAd.startAnimation(animationMovingFromBottom);
                cvWatchAd.setVisibility(View.VISIBLE);
                llContainerWithImageAndTextAdButton.startAnimation(animationChangeAlpha);
                llContainerWithImageAndTextAdButton.setVisibility(View.VISIBLE);
            }

            @Override
            public void onRewardedVideoAdOpened() {

            }

            @Override
            public void onRewardedVideoStarted() {

            }

            @Override
            public void onRewardedVideoAdClosed() {
                Log.e("LOL", "Closed");
            }

            @Override
            public void onRewarded(RewardItem rewardItem) {
                Log.e("LOL", "Награда");
            }

            @Override
            public void onRewardedVideoAdLeftApplication() {

            }

            @Override
            public void onRewardedVideoAdFailedToLoad(int i) {

            }
        });

        fabCloseRequestAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        cvWatchAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mRewardedVideoAd.isLoaded()) {
                    mRewardedVideoAd.show();
                    loadAd();
                } else {
                    Toast.makeText(ActivityRequestOfWatchADVideo.this, "Ролик прогружается, нужно немного подождать)", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void choiseIcon(String nameOfGroup) {
        String[] allNameOfLockGroups = getResources().getStringArray(R.array.lock_groups);
        int idOfDrawable = 0;
        switch (nameOfGroup){
            case allNameOfLockGroups[0]: idOfDrawable = R.drawable.ic_list_of_groups_mcdonalds;
            break;
        }
    }

    private void loadAd() {
        mRewardedVideoAd = MobileAds.getRewardedVideoAdInstance(this);
        mRewardedVideoAd.loadAd(getResources().getString(R.string.admob_award),
                new AdRequest.Builder().build());
    }
}
