package com.wsoteam.diet.BranchOfAnalyzer;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.wsoteam.diet.POJOFoodItem.DbAnalyzer;
import com.wsoteam.diet.POJOFoodItem.FoodConnect;
import com.wsoteam.diet.POJOFoodItem.ListOfFoodItem;
import com.wsoteam.diet.POJOFoodItem.ListOfGroupsOfFood;
import com.wsoteam.diet.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ActivityListAndSearch extends AppCompatActivity {
    private RecyclerView rvListOfSearchResponse;
    private ArrayList<ListOfFoodItem> listOfGroupsFoods = new ArrayList<>();
    private ArrayList<ListOfFoodItem> tempListOfGroupsFoods = new ArrayList<>();
    private EditText edtSearchField;
    private ImageView ivCancel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_and_search);

        ivCancel = findViewById(R.id.ibActivityListAndSearchCollapsingCancelButton);
        rvListOfSearchResponse = findViewById(R.id.rvListOfSearchResponse);
        edtSearchField = findViewById(R.id.edtActivityListAndSearchCollapsingSearchField);
        rvListOfSearchResponse.setLayoutManager(new LinearLayoutManager(ActivityListAndSearch.this));
        AsyncLoadFoodList asyncLoadFoodList = new AsyncLoadFoodList();
        asyncLoadFoodList.execute();

        edtSearchField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                tempListOfGroupsFoods = new ArrayList<>();
                for (int j = 0; j < listOfGroupsFoods.size(); j++) {
                    if (listOfGroupsFoods.get(j).getName().contains(charSequence)) {
                        tempListOfGroupsFoods.add(listOfGroupsFoods.get(j));
                    }
                }
                rvListOfSearchResponse.setAdapter(new ItemAdapter(tempListOfGroupsFoods));

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        ivCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtSearchField.setText("");
            }
        });


    }

    private ArrayList<ListOfFoodItem> fillItemsList(List<ListOfGroupsOfFood> listOfGroups) {
        ArrayList<ListOfFoodItem> items = new ArrayList<>();
        for (int i = 0; i < listOfGroups.size(); i++) {
            ListOfFoodItem itemOfGlobalBaseForWriting;
            for (int j = 0; j < listOfGroups.get(i).getListOfFoodItems().size(); j++) {
                itemOfGlobalBaseForWriting = listOfGroups.get(i).getListOfFoodItems().get(j);
                items.add(itemOfGlobalBaseForWriting);
            }

        }
        return items;
    }

    public class ItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tvName, tvCal;
        private ImageView ivMainImage;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_rv_list_of_search_response, viewGroup, false));
            tvName = itemView.findViewById(R.id.tvName);
            tvCal = itemView.findViewById(R.id.tvCal);
            ivMainImage = itemView.findViewById(R.id.ivImage);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Intent intent = new Intent(ActivityListAndSearch.this, ActivityDetailOfFood.class);
            intent.putExtra("ActivityDetailOfFood", tempListOfGroupsFoods.get(getAdapterPosition()));
            startActivity(intent);
        }

        public void bind(ListOfFoodItem itemOfGlobalBase, boolean isItemForSeparator) {
            tvName.setText(itemOfGlobalBase.getName());
            tvCal.setText(itemOfGlobalBase.getCalories() + " " + getString(R.string.for_100_g_of_product));
            Glide.with(ActivityListAndSearch.this).load(itemOfGlobalBase.getUrlOfImages()).into(ivMainImage);

        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        ArrayList<ListOfFoodItem> itemsOfGlobalBases;

        public ItemAdapter(ArrayList<ListOfFoodItem> itemsOfGlobalBases) {
            this.itemsOfGlobalBases = itemsOfGlobalBases;
        }

        @NonNull
        @Override
        public ItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityListAndSearch.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemHolder holder, int position) {
            if (itemsOfGlobalBases.get(position).getUrlOfImages().equals("0")) {
                holder.bind(itemsOfGlobalBases.get(position), true);
            } else {
                holder.bind(itemsOfGlobalBases.get(position), false);
            }

        }

        @Override
        public int getItemCount() {
            return itemsOfGlobalBases.size();
        }
    }

    private class AsyncLoadFoodList extends AsyncTask<Void, Void, DbAnalyzer> {
        @Override
        protected void onPostExecute(DbAnalyzer dbAnalyzer) {
            listOfGroupsFoods = fillItemsList(dbAnalyzer.getListOfGroupsOfFood());
        }

        @Override
        protected DbAnalyzer doInBackground(Void... voids) {
            String json;
            Moshi moshi = new Moshi.Builder().build();
            JsonAdapter<FoodConnect> jsonAdapter = moshi.adapter(FoodConnect.class);
            try {
                InputStream inputStream = getAssets().open("food_list.json");
                int size = inputStream.available();
                byte[] buffer = new byte[size];
                Log.e("LOL", String.valueOf(size));
                inputStream.read(buffer);
                inputStream.close();
                json = new String(buffer, "UTF-8");

                FoodConnect foodConnect = jsonAdapter.fromJson(json);

                return foodConnect.getDbAnalyzer();
            } catch (Exception e) {

            }
            return null;
        }
    }
}
