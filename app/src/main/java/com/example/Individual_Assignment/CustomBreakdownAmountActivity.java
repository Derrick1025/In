package com.example.Individual_Assignment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class CustomBreakdownAmountActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_breakdown_amount);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        final EditText totalBillEditText = findViewById(R.id.totalBillEditText);
        final EditText person1AmountEditText = findViewById(R.id.person1AmountEditText);
        final EditText person2AmountEditText = findViewById(R.id.person2AmountEditText);
        final EditText person3AmountEditText = findViewById(R.id.person3AmountEditText);
        final EditText person4AmountEditText = findViewById(R.id.person4AmountEditText);
        Button verifyButton = findViewById(R.id.verifyButton);
        final TextView resultTextView = findViewById(R.id.resultTextView);

        verifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String totalBillString = totalBillEditText.getText().toString().trim();
                String person1String = person1AmountEditText.getText().toString().trim();
                String person2String = person2AmountEditText.getText().toString().trim();
                String person3String = person3AmountEditText.getText().toString().trim();
                String person4String = person4AmountEditText.getText().toString().trim();

                // Check if any of the fields are empty
                if (totalBillString.isEmpty()) {
                    totalBillEditText.setError("Please enter the total bill");
                    return;
                }
                if (person1String.isEmpty()) {
                    person1AmountEditText.setError("Please enter the number of person 1");
                    return;
                }
                if (person2String.isEmpty()) {
                    person2AmountEditText.setError("Please enter the number of person 2");
                    return;
                }
                if (person3String.isEmpty()) {
                    person3AmountEditText.setError("Please enter the number of person 3");
                    return;
                }
                if (person4String.isEmpty()) {
                    person4AmountEditText.setError("Please enter the number of person 4");
                    return;
                }

                double totalBill = Double.parseDouble(totalBillEditText.getText().toString());
                double person1Amount = Double.parseDouble(person1AmountEditText.getText().toString());
                double person2Amount = Double.parseDouble(person2AmountEditText.getText().toString());
                double person3Amount = Double.parseDouble(person3AmountEditText.getText().toString());
                double person4Amount = Double.parseDouble(person4AmountEditText.getText().toString());

                double totalIndividualAmounts = person1Amount + person2Amount + person3Amount + person4Amount;

                if (Math.abs(totalBill - totalIndividualAmounts) < 0.01) {
                    resultTextView.setText("The amounts are correctly distributed.");
                }
                else {
                    resultTextView.setText("There's a discrepancy in the amounts. Please check.");
                    resultTextView.setTextColor(Color.RED);
                }
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
