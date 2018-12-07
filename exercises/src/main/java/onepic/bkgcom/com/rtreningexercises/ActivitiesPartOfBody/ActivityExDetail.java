package onepic.bkgcom.com.rtreningexercises.ActivitiesPartOfBody;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import onepic.bkgcom.com.rtreningexercises.POJOs.Ex;
import onepic.bkgcom.com.rtreningexercises.R;

public class ActivityExDetail extends AppCompatActivity {

    private AdView banner;
    private InterstitialAd mInterstitialAd;
    private Ex ex;
    public static final String TAG = "ActivityExDetail";
    private TextView title, basic_muscle, additional_muscle, complexity, for_man, for_woman, detail, main_chips;
    private ImageView imageView, imageViewBackHead;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ex_detail);
        banner = findViewById(R.id.bannerMainActivity);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(this.getResources().getString(R.string.inter));
        //mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        AdRequest adRequest = new AdRequest.Builder().build();
        mInterstitialAd.loadAd(adRequest);
        banner.loadAd(adRequest);

        ex = (Ex) getIntent().getSerializableExtra(TAG);

        imageViewBackHead = findViewById(R.id.ivDetailBack);
        imageView = findViewById(R.id.ivDetailImage);
        title = findViewById(R.id.tvTitleExDetail);
        basic_muscle = findViewById(R.id.tvDetailBasicMuscle);
        additional_muscle = findViewById(R.id.tvDetailAdditionalMuscle);
        complexity = findViewById(R.id.tvDetailComplexity);
        for_man = findViewById(R.id.tvDetailCountMan);
        for_woman = findViewById(R.id.tvDetailCountWoman);
        detail = findViewById(R.id.tvDetailText);
        main_chips = findViewById(R.id.tvDetailChips);

        Glide.with(this).load(ex.getImg_url()).into(imageView);
        Glide.with(this).load(ex.getUrl_of_logo()).into(imageViewBackHead);

        title.setText(ex.getTitle());
        basic_muscle.setText(ex.getBasic_muscle());
        additional_muscle.setText(ex.getAdditional_muscle());
        complexity.setText(ex.getComplexity());
        for_man.setText(ex.getFor_man());
        for_woman.setText(ex.getFor_woman());
        detail.setText(Html.fromHtml(ex.getDetail()));
        main_chips.setText(Html.fromHtml(ex.getMain_chips()));

        title.setTypeface(Typeface.createFromAsset(ActivityExDetail.this.getAssets()
                , "asProgramMainScreen.ttf"));
    }

    @Override
    public void onBackPressed() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onBackPressed();
    }
}
