package com.wsoteam.diet.BranchOfDiary;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.wsoteam.diet.R;

public class ActitityMainActivityOfData extends AppCompatActivity {
    private FloatingActionButton fabAddData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_of_data_diary);

        fabAddData = findViewById(R.id.fabAddDataListOfDiary);


        fabAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActitityMainActivityOfData.this, ActivityAddData.class);
                startActivity(intent);
            }
        });
    }
}
