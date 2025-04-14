package com.example.question1;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText inputValue;
    Spinner fromUnitSpinner, toUnitSpinner;
    Button convertButton;
    TextView resultText;

    String[] units = {"Kilometers", "Meters", "Centimeters"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.inputValue);
        fromUnitSpinner = findViewById(R.id.fromUnitSpinner);
        toUnitSpinner = findViewById(R.id.toUnitSpinner);
        convertButton = findViewById(R.id.convertButton);
        resultText = findViewById(R.id.resultText);

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
            case "Kilometers": meterValue = value * 1000; break;
            case "Meters": meterValue = value; break;
            case "Centimeters": meterValue = value / 100; break;
        }

        // Convert from meters to the target unit
        switch (to) {
            case "Kilometers": return meterValue / 1000;
            case "Meters": return meterValue;
            case "Centimeters": return meterValue * 100;
            default: return meterValue;
        }
    }
}
