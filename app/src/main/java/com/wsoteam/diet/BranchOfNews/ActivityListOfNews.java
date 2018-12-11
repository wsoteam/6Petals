package com.wsoteam.diet.BranchOfNews;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import com.vk.sdk.api.VKApiConst;
import com.vk.sdk.api.VKError;
import com.vk.sdk.api.VKParameters;
import com.vk.sdk.api.VKRequest;
import com.vk.sdk.api.VKResponse;
import com.vk.sdk.api.methods.VKApiGroups;
import com.vk.sdk.api.methods.VKApiWall;
import com.vk.sdk.api.model.VKList;
import com.wsoteam.diet.Config;
import com.wsoteam.diet.POJOSForVkResponse.Item;
import com.wsoteam.diet.POJOSForVkResponse.Response;
import com.wsoteam.diet.R;

import org.json.JSONException;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ActivityListOfNews extends AppCompatActivity {

    private static final int VISIBLE_POSITION = 5;
    private static final int INVISIBLE_POSITION = 3;
    private static final int MAX_COUNT_OF_LETTERS = 50;
    private static String CURRENT_GROUP_ID = "amazingheapkotya";
    private static final String MAIN_GROUP_ID = "amazingheapkotya";
    private static final String GROUP_ID_TEST = "testdbforapp";
    private static String ACCES_TOKEN = "28da670228da670228da6702e528bd27cc228da28da67027325b00cc2cf21e0c7892592";
    private static final int COUNT_OF_ITEM_FROM_RESPONSE = 100;
    private boolean isMainUrl = true;

    private RecyclerView rvListOfNews;
    private SwipeRefreshLayout swipeRefreshLayout;
    private FloatingActionButton fabSwitchUrl;

    private ItemAdapter itemAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_news);
        rvListOfNews = findViewById(R.id.rvListOfNews);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        fabSwitchUrl = findViewById(R.id.fabSwitchUrl);
        fabSwitchUrl.setVisibility(View.GONE);
        rvListOfNews.setLayoutManager(new LinearLayoutManager(this));
        rvListOfNews.setHasFixedSize(false);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getResponseFromVK();
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        getResponseFromVK();

        fabSwitchUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isMainUrl) {
                    Toast.makeText(ActivityListOfNews.this, "Тестовая группа загружена", Toast.LENGTH_SHORT).show();
                    CURRENT_GROUP_ID = GROUP_ID_TEST;
                    getResponseFromVK();
                    isMainUrl = false;
                } else {
                    Toast.makeText(ActivityListOfNews.this, "Рабочая группа загружена", Toast.LENGTH_SHORT).show();
                    CURRENT_GROUP_ID = MAIN_GROUP_ID;
                    getResponseFromVK();
                    isMainUrl = true;
                }
            }
        });

    }

    private void getResponseFromVK() {

        Moshi moshi = new Moshi.Builder().build();
        JsonAdapter<Response> jsonAdapter = moshi.adapter(Response.class);
        VKRequest vkRequest = new VKApiGroups().getById(VKParameters.from(VKApiConst.GROUP_ID, CURRENT_GROUP_ID,
                VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
        vkRequest.executeWithListener(new VKRequest.VKRequestListener() {
            @Override
            public void onError(VKError error) {
                super.onError(error);
                Log.e("LOL", "Error");
            }

            @Override
            public void attemptFailed(VKRequest request, int attemptNumber, int totalAttempts) {
                super.attemptFailed(request, attemptNumber, totalAttempts);
                Log.e("LOL", "attemptFailed");
            }

            @Override
            public void onComplete(VKResponse response) {
                super.onComplete(response);
                //Toast.makeText(getActivity(), R.string.downloaded, Toast.LENGTH_SHORT).show();
                VKList vkList = (VKList) response.parsedModel;
                try {
                    final VKRequest vkRequestWall = new VKApiWall().get(VKParameters.from(VKApiConst.OWNER_ID,
                            "-" + vkList.get(0).fields.getInt("id"), VKApiConst.COUNT, COUNT_OF_ITEM_FROM_RESPONSE,
                            VKApiConst.ACCESS_TOKEN, ACCES_TOKEN));
                    vkRequestWall.executeWithListener(new VKRequest.VKRequestListener() {
                        @Override
                        public void onComplete(VKResponse response) {
                            super.onComplete(response);
                            try {
                                Log.i("RESPONSE", response.responseString);
                                Response responseVk = jsonAdapter.fromJson(response.json.getString("response"));
                                ObjectHolder objectHolder = new ObjectHolder();
                                objectHolder.createObject(responseVk);
                                updateUI();
                            } catch (IOException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateUI() {
        itemAdapter = new ItemAdapter((ArrayList<Item>) ObjectHolder.getResponseVk().getItems());
        itemAdapter.notifyDataSetChanged();
        rvListOfNews.setAdapter(itemAdapter);
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvTitle, tvDate;
        ImageView imageView;
        CardView cardView;
        FloatingActionButton fabShare;
        String urlOfImage = "";

        public ItemViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_of_news_list, viewGroup, false));
            tvTitle = itemView.findViewById(R.id.tvFromItemOfList);
            tvDate = itemView.findViewById(R.id.tvItemListOfNewsDate);
            imageView = itemView.findViewById(R.id.ivItemOfList);
            cardView = imageView.findViewById(R.id.cardFromItemOfList);
            fabShare = itemView.findViewById(R.id.fabItemNewsList);

            itemView.setOnClickListener(this);


        }

        @Override
        public void onClick(View view) {
            /*Intent intent = new Intent(ActivityListOfNews.this, ActivityDetailPhoto.class);
            intent.putExtra(Config.KEY_OF_CLICK_ITEM_DETAIL_NEWS, getAdapterPosition());
            startActivity(intent);*/
        }

        public void bind(Item item) {

            if (item.getAttachments().get(0).getType().equals("photo")) {
                if (item.getText().length() > MAX_COUNT_OF_LETTERS) {
                    tvTitle.setText(item.getText().substring(0, MAX_COUNT_OF_LETTERS) + " ...");
                } else {
                    tvTitle.setText(item.getText());
                }
                if (item.getAttachments().get(0).getPhoto().getPhoto1280() != null) {
                    urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto1280();
                    Glide.with(ActivityListOfNews.this).load(urlOfImage).into(imageView);
                } else {
                    if (item.getAttachments().get(0).getPhoto().getPhoto807() != null) {
                        urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto807();
                        Glide.with(ActivityListOfNews.this).load(urlOfImage).into(imageView);
                    } else {
                        urlOfImage = item.getAttachments().get(0).getPhoto().getPhoto604();
                        Glide.with(ActivityListOfNews.this).load(urlOfImage).into(imageView);
                    }
                }
            } else {
                tvTitle.setText("error");
            }
            tvDate.setText(parseDate(item.getDate()));
            fabShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sharePhoto();
                }
            });
        }

        private String parseDate(long count) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(" HH:mm  dd MMMM", Locale.ENGLISH);
            Date date = new Date(count * 1000);

            return "Опубликовано -" + simpleDateFormat.format(date);
        }

        private void sharePhoto() {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            intent.putExtra(Intent.EXTRA_TEXT, urlOfImage);
            startActivity(intent);
        }
    }

    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        ArrayList<Item> items;

        public ItemAdapter(ArrayList<Item> items) {
            this.items = items;
        }

        @NonNull
        @Override
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(ActivityListOfNews.this);
            Log.e("LOL", "Create");
            return new ItemViewHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
            holder.bind(items.get(position));
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}
