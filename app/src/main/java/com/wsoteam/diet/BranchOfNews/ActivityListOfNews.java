package com.wsoteam.diet.BranchOfNews;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.wsoteam.diet.R;

public class ActivityListOfNews extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_news);

        LoaderForResponseFromVk.getResponseFromVK("amazingheapkotya");

        Toast.makeText(this, String.valueOf(ObjectHolder.getResponseVk().getCount()), Toast.LENGTH_SHORT).show();
    }
}
