package com.wsoteam.diet.BranchOfCalculating;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wsoteam.diet.R;


public class ActivityCalculatorSPK extends AppCompatActivity {
    private EditText edtHeight, edtAge;
    private Button btnLevelLoad, btnCalculate;
    private RadioGroup rgFemaleOrMale;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator_spk);
        edtHeight = findViewById(R.id.edtSpkGrowth);
        edtAge = findViewById(R.id.edtSpkAge);
        btnLevelLoad = findViewById(R.id.btnSpkChoiseLevel);
        btnCalculate = findViewById(R.id.btnSpkCalculate);
        rgFemaleOrMale = findViewById(R.id.rgFemaleOrMaleSpk);


        btnLevelLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialogAboutLevelLoad();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkInputData()) {
                    calculate();
                }
            }
        });
    }

    private boolean checkInputData() {
        if (rgFemaleOrMale.getCheckedRadioButtonId() != -1) {
            if (!edtAge.getText().toString().equals("")
                    && Integer.parseInt(edtAge.getText().toString()) >= 18
                    && Integer.parseInt(edtAge.getText().toString()) <= 200) {
                if (!edtHeight.getText().toString().equals("")
                        && Integer.parseInt(edtHeight.getText().toString()) >= 100
                        && Integer.parseInt(edtHeight.getText().toString()) <= 300) {
                    return true;
                } else {
                    Toast.makeText(ActivityCalculatorSPK.this, R.string.spk_check_your_height, Toast.LENGTH_SHORT).show();
                    return false;
                }
            } else {
                Toast.makeText(ActivityCalculatorSPK.this, R.string.spk_check_your_age, Toast.LENGTH_SHORT).show();
                return false;
            }
        } else {
            Toast.makeText(ActivityCalculatorSPK.this, R.string.spk_choise_your_gender, Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    private void calculate() {
        switch (rgFemaleOrMale.getCheckedRadioButtonId()){
            case R.id.rbS
        }

    }

    private void createAlertDialogAboutLevelLoad() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final View view = View.inflate(this, R.layout.alert_dialog_level, null);
        final RadioGroup rgLevelLoad = view.findViewById(R.id.rgLevelLoad);
        builder.setView(view);
        builder.setPositiveButton("ок", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (rgLevelLoad.getCheckedRadioButtonId() != -1) {
                    RadioButton radioButton = view.findViewById(rgLevelLoad.getCheckedRadioButtonId());
                    btnLevelLoad.setText(radioButton.getText());
                }
            }
        });
        builder.setNeutralButton("отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.show();
    }
}
