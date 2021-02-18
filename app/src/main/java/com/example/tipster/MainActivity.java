package com.example.tipster;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // Widgets in the application
    private EditText txtAmount;
    private EditText txtPeople;
    private EditText txtTipOther;
    private RadioGroup rdoGroupTips;
    private Button btnCalculate;
    private Button btnReset;

    private TextView txtTipAmount;
    private TextView txtTotalToPay;
    private TextView txtTipPerPerson;

    // For the ID of the radio button selected
    private int radioCheckedId = -1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseDisplayContent();
    }

    private void initialiseDisplayContent() {
        txtAmount = findViewById(R.id.txtAmount);
        // On app load, the cursor should be in the Amount field
        txtAmount.requestFocus();

        txtPeople = findViewById(R.id.txtPeople);
        txtTipOther = findViewById(R.id.txtTipOther);

        rdoGroupTips = findViewById(R.id.RadioGroupTips);

        btnCalculate = findViewById(R.id.btnCalculate);
        // On app load, the Calculate button is disabled
        btnCalculate.setEnabled(false);

        btnReset = findViewById(R.id.btnRest);

        txtTipAmount = findViewById(R.id.txtTipAmount);
        txtTotalToPay = findViewById(R.id.txtTotalToPay);
        txtTipPerPerson = findViewById(R.id.txtTipPerPerson);

        // On app load, disable the Other Tip Percentage text field
        txtTipOther.setEnabled(false);
    }
}