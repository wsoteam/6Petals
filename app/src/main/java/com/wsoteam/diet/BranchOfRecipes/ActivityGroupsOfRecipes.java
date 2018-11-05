package com.wsoteam.diet.BranchOfRecipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOS.ListOfGroupsRecipes;
import com.wsoteam.diet.POJOS.ListOfRecipes;
import com.wsoteam.diet.R;

import java.util.ArrayList;

public class ActivityGroupsOfRecipes extends AppCompatActivity {

    private static RecyclerView rvList;
    private static ArrayList<ListOfRecipes> listOfGroupsRecipes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        listOfGroupsRecipes = (ArrayList<ListOfRecipes>) new ListOfGroupsRecipes().getListOfGroupsRecipes();
        rvList = (RecyclerView) findViewById(R.id.rvRecipesList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new RecipeGroupAdapter(listOfGroupsRecipes));
    }

    private class RecipeGroupHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitleGropeRecipe;
        ImageView ivImgRecipe;

        public RecipeGroupHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_groups_of_recipe, viewGroup, false));
            itemView.setOnClickListener(this);
            tvTitleGropeRecipe = itemView.findViewById(R.id.tv_itemGroupsOfRecipe);
            ivImgRecipe = itemView.findViewById(R.id.iv_itemGroupeRecipe);
        }

        public void bind(ListOfRecipes title) {
            tvTitleGropeRecipe.setText(title.getName());
            //Picasso.with(ActivityMonoDiet.this).load(title.getUrl_title()).into(ivItem);
            Glide.with(ActivityGroupsOfRecipes.this).load(title.getUrlGroup()).into(ivImgRecipe);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(ActivityGroupsOfRecipes.this, ActivityRecipes.class);
            intent.putExtra(Config.ID_OF_RECIPE_GROUPS, listOfGroupsRecipes.get(getAdapterPosition()));
            startActivity(intent);
        }
    }

    private class RecipeGroupAdapter extends RecyclerView.Adapter<RecipeGroupHolder> {
        ArrayList<ListOfRecipes> listOfRecipesGroup;


        public RecipeGroupAdapter(ArrayList<ListOfRecipes> titles) {
            listOfRecipesGroup = titles;
        }


        @Override
        public RecipeGroupHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityGroupsOfRecipes.this);
            return new RecipeGroupHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(RecipeGroupHolder holder, int position) {
            holder.bind(listOfRecipesGroup.get(position));
        }

        @Override
        public int getItemCount() {
            return listOfRecipesGroup.size();
        }
    }
}
