package com.wsoteam.diet.BranchOfCalculating;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.appodeal.ads.Appodeal;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.wsoteam.diet.ADsSingleton;
import com.wsoteam.diet.BranchOfMonoDiets.ActivityViewerOfBodyItem;
import com.wsoteam.diet.R;
import com.yandex.metrica.YandexMetrica;

public class ActivityCalculatorIMT extends AppCompatActivity {

    private Button btnCalculate;
    private EditText edtHeight, edtWeight;
    int weight, height;
    String bodyType;
    String[] bodyTypes;
    InterstitialAd interstitialAd;
    private AdView adBanner;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(interstitialAd.isLoaded()){
            interstitialAd.show();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_imt);

        if (ADsSingleton.getInstance().check()) {
            Appodeal.show(this, Appodeal.INTERSTITIAL);
        }
        bodyTypes = getResources().getStringArray(R.array.body_types);

        btnCalculate = findViewById(R.id.btnIMTCalculate);
        edtHeight = findViewById(R.id.edtIMTHeight);
        edtWeight = findViewById(R.id.edtIMTWeight);
        adBanner = findViewById(R.id.bnrCalculatorIMT);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (checkNull()) {
                        createAlertDialog(countIMT());
                    }
                } catch (Exception e) {
                    Toast.makeText(ActivityCalculatorIMT.this, R.string.unknown_error, Toast.LENGTH_SHORT).show();
                }
            }
        });

        adBanner.loadAd(new AdRequest.Builder().build());
        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getResources().getString(R.string.admob_interstitial));
        interstitialAd.loadAd(new AdRequest.Builder().build());

        YandexMetrica.reportEvent("Открыт экран: Калькулятор ИМТ");

    }

    private String countIMT() {
        double coefficient = weight / (double) ((height / 100) ^ 2);
        String bodyTypeForShow = "";
        if (coefficient < 16) {
            bodyTypeForShow = bodyTypes[0];
        }
        if (coefficient >= 16 && coefficient <= 19) {
            bodyTypeForShow = bodyTypes[1];
        }
        if (coefficient > 19 && coefficient <= 25) {
            bodyTypeForShow = bodyTypes[2];
        }
        if (coefficient > 25 && coefficient <= 30) {
            bodyTypeForShow = bodyTypes[3];
        }
        if (coefficient > 30 && coefficient <= 35) {
            bodyTypeForShow = bodyTypes[4];
        }
        if (coefficient > 35 && coefficient <= 40) {
            bodyTypeForShow = bodyTypes[5];
        }
        if (coefficient > 40) {
            bodyTypeForShow = bodyTypes[6];
        }
        Toast.makeText(this, String.valueOf(coefficient), Toast.LENGTH_SHORT).show();
        return bodyTypeForShow;
    }

    private boolean checkNull() {
        if (edtWeight == null || edtHeight == null) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            height = Integer.parseInt(edtHeight.getText().toString());
            weight = Integer.parseInt(edtWeight.getText().toString());
            return true;
        }
    }

    private void createAlertDialog(String bodyType) {
        TextView tvAlertDialogWeight, tvTitleOfAlertDialog;
        FloatingActionButton btnOk;

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog alertDialog = builder.create();
        View view = getLayoutInflater().inflate(R.layout.alert_dialog_weight, null);
        tvAlertDialogWeight = view.findViewById(R.id.tvAlertDialogWeight);
        tvTitleOfAlertDialog = view.findViewById(R.id.tvTitleAlertDialogWeight);
        tvTitleOfAlertDialog.setText("Ваш ИМТ");
        btnOk = view.findViewById(R.id.btnAlertDialogOk);
        tvAlertDialogWeight.setText(bodyType);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.cancel();
            }
        });
        alertDialog.setView(view);
        alertDialog.show();

    }
}
