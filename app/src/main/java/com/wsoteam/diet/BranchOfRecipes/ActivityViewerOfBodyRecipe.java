package com.wsoteam.diet.BranchOfRecipes;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;

import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOS.ItemRecipes;
import com.wsoteam.diet.R;

public class ActivityViewerOfBodyRecipe extends AppCompatActivity{

    TextView tvTitle, tvBody;
    ImageView imageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewer_of_body_recipe);
        getSupportActionBar().hide();

        tvTitle = findViewById(R.id.tvTitleRecipeItem);
        tvBody = findViewById(R.id.tvBodyRecipeItem);
        imageView = findViewById(R.id.ivHeadOfViewerRecipeItem);


        AdRequest adRequest = new AdRequest.Builder().build();


        ItemRecipes recipes = (ItemRecipes) getIntent().getSerializableExtra(Config.ID_OF_RECIPE);

//        tvTitle.setText(itemOb.getName());
        tvTitle.setText(Html.fromHtml(recipes.getName()));
//        tvBody.setText(itemOb.getBodyOfText());
        tvBody.setText(Html.fromHtml(recipes.getBody()));
        Picasso.with(this).load(recipes.getUrl()).into(imageView);
    }

}
