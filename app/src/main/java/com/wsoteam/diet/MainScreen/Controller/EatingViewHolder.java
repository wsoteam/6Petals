package com.wsoteam.diet.MainScreen.Controller;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.wsoteam.diet.BranchOfAnalyzer.POJOEating.Eating;
import com.wsoteam.diet.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EatingViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tvTitleOfEatingCard) TextView tvTitleOfEatingCard;
    @BindView(R.id.rvListOfFoodEatingCard) RecyclerView rvListOfFoodEatingCard;
    @BindView(R.id.ibtnOpenList) ImageButton ibtnOpenList;
    @BindView(R.id.tvSumOfKcal) TextView tvSumOfKcal;

    public EatingViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
        super(layoutInflater.inflate(R.layout.ms_item_eating_list, viewGroup, false));
        ButterKnife.bind(this, itemView);
    }

    public void bind(List<Eating> eatingGroup, Context context, String nameOfEatingGroup) {
        tvSumOfKcal.setText(String.valueOf(calculateCalories(eatingGroup)) + " Ккал");
        tvTitleOfEatingCard.setText(nameOfEatingGroup);
        //rvListOfFoodEatingCard.setLayoutManager(new LinearLayoutManager(context));

    }

    private int calculateCalories(List<Eating> eatingGroup) {
        int sum = 0;
        for (int i = 0; i < eatingGroup.size(); i++) {
            sum += eatingGroup.get(i).getCalories();
        }
        return sum;
    }
}
