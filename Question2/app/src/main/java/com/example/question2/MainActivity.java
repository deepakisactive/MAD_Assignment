package com.example.question2;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertButton;
    TextView resultText;

    String[] units = {"Feet", "Inches", "Centimeters", "Meters", "Yards"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

        Button settingsButton = findViewById(R.id.settingsButton);
        settingsButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(intent);
        });

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        fromUnitSpinner.setAdapter(adapter);
        toUnitSpinner.setAdapter(adapter);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputStr = inputValue.getText().toString();
                if (inputStr.isEmpty()) {
                    resultText.setText("Please enter a value.");
                    return;
                }

                double input = Double.parseDouble(inputStr);
                String fromUnit = fromUnitSpinner.getSelectedItem().toString();
                String toUnit = toUnitSpinner.getSelectedItem().toString();
                double result = convertUnits(input, fromUnit, toUnit);
                resultText.setText(String.format("%.2f %s", result, toUnit));
            }
        });
    }

    private double convertUnits(double value, String from, String to) {
        double meterValue = value;

        // Convert from any unit to meters first
        switch (from) {
            case "Feet":
                meterValue = value * 0.3048;
                break;  // Feet to Meters
            case "Inches":
                meterValue = value * 0.0254;
                break; // Inches to Meters
            case "Centimeters":
                meterValue = value / 100;
                break; // Centimeters to Meters
            case "Meters":
                meterValue = value;
                break; // Meters to Meters
            case "Yards":
                meterValue = value * 0.9144;
                break; // Yards to Meters
        }

        // Convert from meters to the target unit
        switch (to) {
            case "Feet":
                return meterValue / 0.3048; // Meters to Feet
            case "Inches":
                return meterValue / 0.0254; // Meters to Inches
            case "Centimeters":
                return meterValue * 100; // Meters to Centimeters
            case "Meters":
                return meterValue; // Meters to Meters
            case "Yards":
                return meterValue / 0.9144; // Meters to Yards
            default:
                return meterValue;
        }
    }
}
