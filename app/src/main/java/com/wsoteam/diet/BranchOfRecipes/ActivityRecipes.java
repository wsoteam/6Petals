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
import com.wsoteam.diet.POJOS.ItemRecipes;
import com.wsoteam.diet.POJOS.ListOfRecipes;
import com.wsoteam.diet.R;


import java.io.Serializable;
import java.util.ArrayList;

public class ActivityRecipes extends AppCompatActivity {

    private static RecyclerView rvList;
    private static ArrayList<ItemRecipes> listOfRecipes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipes);

        ListOfRecipes li = (ListOfRecipes) getIntent().getSerializableExtra(Config.ID_OF_RECIPE_GROUPS);
        listOfRecipes = (ArrayList<ItemRecipes>) li.getListRecipes();

        rvList = (RecyclerView) findViewById(R.id.rvRecipesList);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new RecipeAdapter(listOfRecipes));
    }


    private class RecipeHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvItemRecipe;
        TextView tvItemBodyRecipe;
        ImageView ivItemRecipe;

        public RecipeHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_of_recipe_list, viewGroup, false));
            itemView.setOnClickListener(this);
            tvItemRecipe = itemView.findViewById(R.id.tv_itemRecipe);
            tvItemBodyRecipe = itemView.findViewById(R.id.tv_bodyRecipe);
            ivItemRecipe = itemView.findViewById(R.id.iv_itemRecipe);
        }

        public void bind(ItemRecipes title) {
            tvItemRecipe.setText(title.getName());
            tvItemBodyRecipe.setText(Html.fromHtml(title.getBody()));
            //Picasso.with(ActivityMonoDiet.this).load(title.getUrl_title()).into(ivItem);
            Glide.with(ActivityRecipes.this).load(title.getUrl()).into(ivItemRecipe);
        }

        @Override
        public void onClick(View v) {
//            Intent intent = new Intent(ActivityRecipes.this, ActivityViewerOfBodyRecipe.class);
//            intent.putExtra(Config.ID_OF_RECIPE, listOfRecipes.get(getAdapterPosition()));
//            startActivity(intent);
        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
        ArrayList<ItemRecipes> listOfRecipes;


        public RecipeAdapter(ArrayList<ItemRecipes> titles) {
            /*this.*/listOfRecipes = titles; // нахрена ключ слово this? это не ошибка но оно требуется когда у двух переменных одинаковые имена
        }


        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityRecipes.this);
            return new RecipeHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder( RecipeHolder holder, int position) {
            holder.bind(listOfRecipes.get(position));
        }

        @Override
        public int getItemCount() {
            return listOfRecipes.size();
        }
    }


}
