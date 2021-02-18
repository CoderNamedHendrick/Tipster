package com.example.tipster;

import androidx.appcompat.app.AppCompatActivity;

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialiseDisplayContent();
        rdoGroupTips.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
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
            }
        });

        txtAmount.setOnKeyListener(mKeyListener);
        txtPeople.setOnKeyListener(mKeyListener);
        txtTipOther.setOnKeyListener(mKeyListener);

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