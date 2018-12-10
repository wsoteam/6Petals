package com.wsoteam.diet.BranchOfNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOSForVkResponse.Item;
import com.wsoteam.diet.R;

public class ActivityDetailPhoto extends AppCompatActivity {
    private ImageView ivMainImage;
    private Item item;
    private String urlOfImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_photo);

        item = ObjectHolder.getResponseVk().getItems().get(getIntent().getIntExtra(Config.KEY_OF_CLICK_ITEM_DETAIL_NEWS, 0));

        ivMainImage = findViewById(R.id.ivMainImageDetailActivity);
        parseImage();
    }

    private void parseImage(){
        if (item.getAttachments().get(0).getPhoto().getPhoto1280() != null) {
            urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto1280();
            Glide.with(this).load(urlOfImage).into(ivMainImage);
        } else {
            if (item.getAttachments().get(0).getPhoto().getPhoto807() != null) {
                urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto807();
                Glide.with(this).load(urlOfImage).into(ivMainImage);
            } else {
                urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto604();
                Glide.with(this).load(urlOfImage).into(ivMainImage);
            }
        }
    }
}
