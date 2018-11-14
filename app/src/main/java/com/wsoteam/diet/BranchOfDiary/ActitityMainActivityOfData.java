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
import com.wsoteam.diet.POJOForDB.DiaryData;
import com.wsoteam.diet.POJOS.POJO;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActitityMainActivityOfData extends AppCompatActivity {
    private FloatingActionButton fabAddData;
    private RecyclerView recyclerView;
    private ArrayList<DiaryData> diaryDataArrayList = new ArrayList<>();

    @Override
    protected void onResume() {
        super.onResume();
        updateUI();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity_of_data_diary);


        fabAddData = findViewById(R.id.fabAddDataListOfDiary);
        recyclerView = findViewById(R.id.rvListOfDiary);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        updateUI();


        fabAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActitityMainActivityOfData.this, ActivityAddData.class);
                startActivity(intent);
            }
        });
    }

    private void updateUI() {
        diaryDataArrayList = (ArrayList<DiaryData>) DiaryData.listAll(DiaryData.class);
        bubbleSort();
        recyclerView.setAdapter(new ItemAdapter(diaryDataArrayList));
    }


    private class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvDay, tvMonth, tvWeight, tvSubWeight, tvChest, tvWaist, tvHips, tvSubChest, tvSubWaist, tvSubHips;
        private ImageView ivLeftColor;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_diary_data, viewGroup, false));
            tvDay = itemView.findViewById(R.id.tvItemDiaryDataNumber);
            tvMonth = itemView.findViewById(R.id.tvItemDiaryDataMonth);
            tvWeight = itemView.findViewById(R.id.tvItemDiaryDataWeight);
            tvSubWeight = itemView.findViewById(R.id.tvItemDiaryDataSubWeight);
            tvChest = itemView.findViewById(R.id.tvItemDiaryDataChestCount);
            tvWaist = itemView.findViewById(R.id.tvItemDiaryDataWaistCount);
            tvHips = itemView.findViewById(R.id.tvItemDiaryDataHipsCount);
            ivLeftColor = itemView.findViewById(R.id.ivColorAboutWeight);
            tvSubChest = itemView.findViewById(R.id.tvItemDiaryDataChestCountSub);
            tvSubWaist = itemView.findViewById(R.id.tvItemDiaryDataWaistCountSub);
            tvSubHips = itemView.findViewById(R.id.tvItemDiaryDataHipsCountSub);
        }

        public void bind(DiaryData currentDiaryData, DiaryData previousDiaryData) {
            tvDay.setText(String.valueOf(currentDiaryData.getNumberOfDay()));
            tvMonth.setText(currentDiaryData.getNameOfMonth());
            tvWeight.setText(String.valueOf(currentDiaryData.getWeight()) + " кг");

            if (currentDiaryData.getChest() != 0) {
                tvChest.setText(String.valueOf(currentDiaryData.getChest()));
            } else {
                tvChest.setText("--");
            }
            if (currentDiaryData.getHips() != 0) {
                tvHips.setText(String.valueOf(currentDiaryData.getHips()));
            } else {
                tvHips.setText("--");
            }
            if (currentDiaryData.getWaist() != 0) {
                tvWaist.setText(String.valueOf(currentDiaryData.getWaist()));
            } else {
                tvWaist.setText("--");
            }

            if (previousDiaryData.getOwnId() == 0) {
                ivLeftColor.setColorFilter(getResources().getColor(R.color.yellow));
                tvSubWeight.setText("--");
            } else {
                if (previousDiaryData.getWeight() > currentDiaryData.getWeight()) {
                    double difference = previousDiaryData.getWeight() - currentDiaryData.getWeight();
                    ivLeftColor.setColorFilter(getResources().getColor(R.color.green));
                    tvSubWeight.setText("-" + String.valueOf(difference));
                    tvSubWeight.setTextColor(getResources().getColor(R.color.green));
                } else {
                    if (previousDiaryData.getWeight() == currentDiaryData.getWeight()) {
                        ivLeftColor.setColorFilter(getResources().getColor(R.color.yellow));
                        tvSubWeight.setText("0");
                        tvSubWeight.setTextColor(getResources().getColor(R.color.yellow));
                    } else {
                        double difference = currentDiaryData.getWeight() - previousDiaryData.getWeight();
                        ivLeftColor.setColorFilter(getResources().getColor(R.color.red));
                        tvSubWeight.setText("+" + String.valueOf(difference));
                        tvSubWeight.setTextColor(getResources().getColor(R.color.red));
                    }
                }

                if (previousDiaryData.getChest() != 0) {
                    toCompateTwoNumbers(tvSubChest, previousDiaryData.getChest(), currentDiaryData.getChest());
                } else {
                    tvSubChest.setText("");
                }
                if (previousDiaryData.getHips() != 0) {
                    toCompateTwoNumbers(tvSubHips, previousDiaryData.getHips(), currentDiaryData.getHips());
                } else {
                    tvSubHips.setText("");
                }
                if (previousDiaryData.getWaist() != 0) {
                    toCompateTwoNumbers(tvSubWaist, previousDiaryData.getWaist(), currentDiaryData.getWaist());
                } else {
                    tvSubWaist.setText("");
                }
            }


        }

        private void toCompateTwoNumbers(TextView currentSubTextView, int previousNumber, int currentNumber) {
            int different = 0;
            if (previousNumber > currentNumber) {
                different = previousNumber - currentNumber;
                currentSubTextView.setTextColor(getResources().getColor(R.color.green));
                currentSubTextView.setText("-" + String.valueOf(different));
            } else if (previousNumber == currentNumber) {
                currentSubTextView.setText("0");
                currentSubTextView.setTextColor(getResources().getColor(R.color.yellow));
            } else {
                different = currentNumber - previousNumber;
                currentSubTextView.setTextColor(getResources().getColor(R.color.red));
                currentSubTextView.setText("+" + String.valueOf(different));
            }
        }

    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        ArrayList<DiaryData> diaryDataArrayList;

        public ItemAdapter(ArrayList<DiaryData> diaryDataArrayList) {
            this.diaryDataArrayList = diaryDataArrayList;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActitityMainActivityOfData.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            if (position + 1 != diaryDataArrayList.size()) {
                holder.bind(diaryDataArrayList.get(position), diaryDataArrayList.get(position + 1));
            } else {
                holder.bind(diaryDataArrayList.get(position), new DiaryData());
            }
        }

        @Override
        public int getItemCount() {
            return diaryDataArrayList.size();
        }
    }

    private void bubbleSort() {
        if (diaryDataArrayList.size() > 1) {
            DiaryData[] arrayForWrite = new DiaryData[diaryDataArrayList.size()];

            for (int i = 0; i < diaryDataArrayList.size(); i++) {
                arrayForWrite[i] = diaryDataArrayList.get(i);
            }

            int lenght = arrayForWrite.length;
            for (int i = 0; i < lenght - 1; i++) {
                for (int j = 0; j < lenght - i - 1; j++) {
                    if (arrayForWrite[j].getOwnId() < arrayForWrite[j + 1].getOwnId()) {
                        DiaryData temp = arrayForWrite[j];
                        arrayForWrite[j] = arrayForWrite[j + 1];
                        arrayForWrite[j + 1] = temp;
                    }
                }
            }
            diaryDataArrayList = new ArrayList<>();
            for (int i = 0; i < arrayForWrite.length; i++) {
                diaryDataArrayList.add(arrayForWrite[i]);
            }
        }

    }


}
