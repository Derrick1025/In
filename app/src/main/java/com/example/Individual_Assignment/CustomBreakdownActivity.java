package com.example.Individual_Assignment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class CustomBreakdownActivity extends AppCompatActivity {
    private EditText totalBillEditText;
    private EditText ratiosEditText;
    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_breakdown);

        setTitle("Split Bill");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        totalBillEditText = findViewById(R.id.totalBill);
        ratiosEditText = findViewById(R.id.ratios);
        resultTextView = findViewById(R.id.result);

        Button calculateButton = findViewById(R.id.calculateButton);
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String totalBillString = totalBillEditText.getText().toString().trim();
                String ratioCheck = ratiosEditText.getText().toString().trim();

                // Check if any of the fields are empty
                if (totalBillString.isEmpty()) {
                    totalBillEditText.setError("Please enter the total bill");
                    return;
                }
                if (ratioCheck.isEmpty()) {
                    ratiosEditText.setError("Please enter the ratio");
                    return;
                }

                double totalBill = Double.parseDouble(totalBillEditText.getText().toString());
                String[] ratiosStrings = ratiosEditText.getText().toString().split(",");
                int[] ratios = new int[ratiosStrings.length];
                for (int i = 0; i < ratiosStrings.length; i++) {
                    ratios[i] = Integer.parseInt(ratiosStrings[i]);
                }

                double totalRatios = 0;
                for (int ratio : ratios) {
                    totalRatios += ratio;
                }

                String result = "";
                for (int i = 0; i < ratios.length; i++) {
                    double personBill = totalBill * ratios[i] / totalRatios;
                    result += "Person " + (i + 1) + " pays: RM" + String.format("%.2f", personBill) + "\n";
                }

                resultTextView.setText(result);

                SharedPreferences pref = getSharedPreferences("appData", MODE_PRIVATE);
                //String existingBreakdown = pref.getString("billBreakdown", "");

                //String combinedData = existingBreakdown + "\n" + result;

                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Custom billBreakdown", result);
                editor.commit();


            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Go back to the parent activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}