package com.wsoteam.diet.BranchOfAnalyzer;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.wsoteam.diet.POJOS.ItemOfGlobalBase;
import com.wsoteam.diet.POJOS.ListOfGroupsFood;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActivityListAndSearch extends AppCompatActivity {
    private RecyclerView rvListOfSearchResponse;
    private ImageView ivLoadingCircle;
    private ArrayList<ListOfGroupsFood> listOfGroupsFoods = new ArrayList<>();

    private final String CURRENT_KEY = "AIzaSyADU2Cs4dGGLxQ0rgLPlTaEDQRwdCpuonk";
    private final String MOBILE_APP_ID = "1:47762729194:android:9e20405dfc5e7aea";
    private final String FIREBASE_URL = "https://fortestload.firebaseio.com";
    private boolean isLoadedEarly = false;
    private final String FIREBASE_APP_NAME = "data_of_analyzer";
    private Animation animationRotate;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_and_search);

        rvListOfSearchResponse = findViewById(R.id.rvListOfSearchResponse);
        ivLoadingCircle = findViewById(R.id.ivLoadingCircleSearchList);

        animationRotate = AnimationUtils.loadAnimation(this, R.anim.animation_rotate);
        ivLoadingCircle.startAnimation(animationRotate);


        for (int i = 0; i < FirebaseApp.getApps(this).size(); i++) {
            if (FirebaseApp.getApps(this).get(i).getName().equals(FIREBASE_APP_NAME)) {
                isLoadedEarly = true;
            }
        }

        if (!isLoadedEarly) {
            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setApplicationId(CURRENT_KEY)
                    .setApiKey(MOBILE_APP_ID)
                    .setDatabaseUrl(FIREBASE_URL)
                    .build();
            FirebaseApp.initializeApp(this, options, FIREBASE_APP_NAME);
        }


        FirebaseApp app = FirebaseApp.getInstance(FIREBASE_APP_NAME);
        FirebaseDatabase database = FirebaseDatabase.getInstance(app);
        DatabaseReference myRef = database.getReference("dbAnalyzer");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfGroupsFoods.add(dataSnapshot.getValue(ListOfGroupsFood.class));
                rvListOfSearchResponse.setLayoutManager(new LinearLayoutManager(ActivityListAndSearch.this));
                rvListOfSearchResponse.setAdapter(new ItemAdapter(fillItemsList(listOfGroupsFoods.get(0))));
                ivLoadingCircle.clearAnimation();
                ivLoadingCircle.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private ArrayList<ItemOfGlobalBase> fillItemsList(ListOfGroupsFood listOfGroupsFood) {
        ItemOfGlobalBase itemForGroupNaming;
        ArrayList<ItemOfGlobalBase> items = new ArrayList<>();
        for (int i = 0; i < listOfGroupsFood.getListOfGroupsOfFood().size(); i++) {
            /*itemForGroupNaming = new ItemOfGlobalBase(listOfGroupsFood.getListOfGroupsOfFood().get(i).getName(),
                    "0", "0", "0", "0",
                    "0", "0", "0","0");*/

            /*items.add(itemForGroupNaming);*/
            ItemOfGlobalBase itemOfGlobalBaseForWriting;
            for (int j = 0; j < listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().size(); j++) {
                itemOfGlobalBaseForWriting = listOfGroupsFood.getListOfGroupsOfFood().get(i).getListOfFoodItems().get(j);
                items.add(itemOfGlobalBaseForWriting);
            }

        }
        Log.e("LOL", String.valueOf(items.size()));
        return items;
    }

    public class ItemHolder extends RecyclerView.ViewHolder {
        private TextView tvName, tvCal, tvProt, tvFat, tvCarbo, tvNumber;
        private ImageView ivMainImage;

        public ItemHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_rv_list_of_search_response, viewGroup, false));
            tvName = itemView.findViewById(R.id.tvName);
            tvCal = itemView.findViewById(R.id.tvCal);
            tvProt = itemView.findViewById(R.id.tvProtein);
            tvFat = itemView.findViewById(R.id.tvFat);
            tvCarbo = itemView.findViewById(R.id.tvCarbohydrates);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            ivMainImage = itemView.findViewById(R.id.ivImage);
        }

        public void bind(ItemOfGlobalBase itemOfGlobalBase, boolean isItemForSeparator) {
            if (isItemForSeparator){
                tvCal.setVisibility(View.GONE);
                tvProt.setVisibility(View.GONE);
                tvFat.setVisibility(View.GONE);
                tvCarbo.setVisibility(View.GONE);
                tvName.setText(itemOfGlobalBase.getName());
            }else {
                tvName.setText(itemOfGlobalBase.getName());
                tvCal.setText(itemOfGlobalBase.getCalories());
                tvProt.setText(itemOfGlobalBase.getProtein());
                tvFat.setText(itemOfGlobalBase.getFat());
                tvCarbo.setText(itemOfGlobalBase.getCarbohydrates());
                tvNumber.setText(String.valueOf(getAdapterPosition()));
                Glide.with(ActivityListAndSearch.this).load(itemOfGlobalBase.getUrl_of_images()).into(ivMainImage);
            }

        }
    }

    public class ItemAdapter extends RecyclerView.Adapter<ItemHolder> {
        ArrayList<ItemOfGlobalBase> itemsOfGlobalBases;

        public ItemAdapter(ArrayList<ItemOfGlobalBase> itemsOfGlobalBases) {
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
            if (itemsOfGlobalBases.get(position).getUrl_of_images().equals("0")){
                holder.bind(itemsOfGlobalBases.get(position), true);
            }else {
                holder.bind(itemsOfGlobalBases.get(position), false);
            }

        }

        @Override
        public int getItemCount() {
            return itemsOfGlobalBases.size();
        }
    }
}
