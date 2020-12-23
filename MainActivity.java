package com.parbon.bmicalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextView resultText;
    private RadioButton radioMale;
    private RadioButton radioFemale;
    private EditText textAge;
    private EditText textFeet;
    private EditText textInch;
    private EditText textWeight;
    private Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        setupButtonClickListener();
    }

    private void findViews() {
        resultText = findViewById(R.id.text_view_result);

        radioMale = findViewById(R.id.radio_button_male);
        radioFemale = findViewById(R.id.radio_button_female);

        textAge = findViewById(R.id.edit_text_age);
        textFeet = findViewById(R.id.edit_text_feet);
        textInch = findViewById(R.id.edit_text_inches);
        textWeight = findViewById(R.id.edit_text_weight);

        calculateButton = findViewById(R.id.button_calculate);
    }

    private void setupButtonClickListener() {
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double bmiResult = calculateBmi();

                String ageText = textAge.getText().toString();
                int age = Integer.parseInt(ageText);
                if (age >= 18) {
                    displayResult(bmiResult);
                } else {
                    guidance(bmiResult);
                }


            }
        });
    }


    private double calculateBmi() {

        String feetText = textFeet.getText().toString();
        String inchText = textInch.getText().toString();
        String weightText = textWeight.getText().toString();

        // converting the strings into integers
        int feet = Integer.parseInt(feetText);
        int inches = Integer.parseInt(inchText);
        int kg = Integer.parseInt(weightText);

        int totalInches = inches + feet * 12;

        // the formula for converting inches to meters
        double heightInMeters = totalInches * 0.0254;

        //formula for BMI
        return kg / (heightInMeters * heightInMeters);

    }

    private void displayResult(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);

        String fullResultString;

        if (bmi < 18.5) {
            //Display underweight
            fullResultString = bmiTextResult + "You are underweight";
        } else if (bmi > 25) {
            //Display overweight
            fullResultString = bmiTextResult + "You are overweight";
        } else {
            //Display healthy
            fullResultString = bmiTextResult + "You are healthy";
        }
        resultText.setText(fullResultString);
    }


    private void guidance(double bmi) {
        DecimalFormat myDecimalFormatter = new DecimalFormat("0.00");
        String bmiTextResult = myDecimalFormatter.format(bmi);
        String fullResultString;
        if (radioMale.isChecked()) {
            //display male guidance
            fullResultString = bmiTextResult + " As you are under 18, please consult with your doctor for the healthy range for boys";
        } else if (radioFemale.isChecked()) {
            //display female guidance
            fullResultString = bmiTextResult + " As you are under 18, please consult with your doctor for the healthy range for girls";
        } else {
            //display general guidance
            fullResultString = bmiTextResult + " As you are under 18, please consult with your doctor for the healthy range for";
        }
        resultText.setText(fullResultString);


    }


}