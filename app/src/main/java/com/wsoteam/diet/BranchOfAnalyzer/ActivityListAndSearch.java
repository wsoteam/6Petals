package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.wsoteam.diet.R;

public class ActivityListAndSearch extends AppCompatActivity {
    private RecyclerView rvListOfSearchResponse;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_and_search);


    }
}
