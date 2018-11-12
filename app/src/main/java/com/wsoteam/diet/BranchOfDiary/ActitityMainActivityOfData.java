package com.wsoteam.diet.BranchOfDiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityMonoDiet;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityViewerOfBodyItem;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOS.POJO;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActitityMainActivityOfData extends AppCompatActivity {
    private FloatingActionButton fabAddData;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_of_data_diary);

        fabAddData = findViewById(R.id.fabAddDataListOfDiary);
        recyclerView = findViewById(R.id.rvListOfDiary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new ItemAdapter());


        fabAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActitityMainActivityOfData.this, ActivityAddData.class);
                startActivity(intent);
            }
        });
    }


    private class ItemHolder extends RecyclerView.ViewHolder {
        TextView tvItem;
        ImageView ivItem;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_diary_data, viewGroup, false));
        }

        public void bind(POJO title) {
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {


        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActitityMainActivityOfData.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 10;
        }
    }


}
