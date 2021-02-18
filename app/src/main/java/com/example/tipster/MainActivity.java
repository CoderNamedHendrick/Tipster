package com.example.tipster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
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

    private View.OnKeyListener mKeyListener = new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            switch (v.getId()){
                case R.id.txtAmount:
                    ;
                case R.id.txtPeople:
                    btnCalculate.setEnabled(txtAmount.getText().length()>0
                    && txtPeople.getText().length()>0);
                case R.id.txtTipOther:
                    btnCalculate.setEnabled(txtAmount.getText().length()>0
                    && txtPeople.getText().length()>0
                    && txtTipOther.getText().length()>0);
                    break;
            }
            return false;
        }
    };

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnCalculate)
                calculate();
            else
                reset();
        }
    };

    private void reset() {
        txtTipAmount.setText("");
        txtTotalToPay.setText("");
        txtTipPerPerson.setText("");
        txtAmount.setText("");
        txtPeople.setText("");
        txtTipOther.setText("");
        rdoGroupTips.clearCheck();
        rdoGroupTips.check(R.id.radioFifteen);
        // Set focus on the first field
        txtAmount.requestFocus();
    }

    private void calculate() {
        Double billAmount = Double.parseDouble(txtAmount.getText().toString());
        Double totalPeople = Double.parseDouble(txtPeople.getText().toString());
        Double percentage = null;
        boolean isError = false;

        if (billAmount < 1.0){
            showErrorAlert("Enter a valid Total Amount.", txtAmount.getId());
            isError = true;
        }

        if (totalPeople < 1.0){
            showErrorAlert("Enter a valid value for No. of people.", txtPeople.getId());
            isError = true;
        }

        if (radioCheckedId == -1)
            radioCheckedId = rdoGroupTips.getCheckedRadioButtonId();
        if (radioCheckedId == R.id.radioFifteen)
            percentage = 15.00;
        else if (radioCheckedId == R.id.radioTwenty)
            percentage = 20.00;
        else if (radioCheckedId == R.id.radioOther){
            percentage = Double.parseDouble(txtTipOther.getText().toString());
            if (percentage < 1.0){
                showErrorAlert("Enter a valid Tip percentage", txtTipOther.getId());
                isError = true;
            }
        }

        if (!isError){
            Double tipAmount = ((billAmount * percentage) /100);
            Double totalToPay = billAmount + tipAmount;
            Double perPersonPays = totalToPay / totalPeople;

            txtTipAmount.setText(tipAmount.toString());
            txtTotalToPay.setText(totalToPay.toString());
            txtTipPerPerson.setText(perPersonPays.toString());
        }
    }

    private void showErrorAlert(String message, int id) {
        new AlertDialog.Builder(this).setTitle("Error")
                .setMessage(message).setNeutralButton("Close",
                (dialog, which) -> findViewById(id).requestFocus()).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseDisplayContent();
        rdoGroupTips.setOnCheckedChangeListener((group, checkedId) -> {
            // Enable/disable Other Tip Percentage field
            if (checkedId == R.id.radioFifteen || checkedId == R.id.radioTwenty){
                txtTipOther.setEnabled(false);
                /**
                 * Enable the Calculate button if total Amount and NO. of
                 * People fields have valid values.
                 */
                btnCalculate.setEnabled(txtAmount.getText().length()>0
                & txtPeople.getText().length()>0);
            }else if (checkedId == R.id.radioOther){
                // Enable the Other Tip Percentage field
                txtTipOther.setEnabled(true);
                // Set the focus to this field
                txtTipOther.requestFocus();
                /*
                * Here we enable the calculate button if the Total Amount and No.
                * of People fields have valid values. Also ensure that user
                * has entered an Other Tip Percentage value before enabling
                * the Calculate button.
                */
                btnCalculate.setEnabled(txtAmount.getText().length()>0
                && txtPeople.getText().length()>0
                && txtTipOther.getText().length()>0);
            }
            // To determine the tip percentage choice made by user
            radioCheckedId = checkedId;
        });

        txtAmount.setOnKeyListener(mKeyListener);
        txtPeople.setOnKeyListener(mKeyListener);
        txtTipOther.setOnKeyListener(mKeyListener);

        btnCalculate.setOnClickListener(mOnClickListener);
        btnReset.setOnClickListener(mOnClickListener);

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