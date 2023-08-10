package com.example.Individual_Assignment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class EqualBreakdownActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equal_breakdown);

        setTitle("Split Bill");

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Button calculateButton = findViewById(R.id.calculate_button);
        final EditText totalBillEditText = findViewById(R.id.total_bill);
        final EditText numPeopleEditText = findViewById(R.id.num_people);
        final TextView resultTextView = findViewById(R.id.result);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String totalBillString = totalBillEditText.getText().toString().trim();
                String numPeopleString = numPeopleEditText.getText().toString().trim();

                // Check if any of the fields are empty
                if (totalBillString.isEmpty()) {
                    totalBillEditText.setError("Please enter the total bill");
                    return;
                }
                if (numPeopleString.isEmpty()) {
                    numPeopleEditText.setError("Please enter the number of people");
                    return;
                }

                double totalBill = Double.parseDouble(totalBillEditText.getText().toString());
                int numPeople = Integer.parseInt(numPeopleEditText.getText().toString());
                double amountPerPerson = totalBill / numPeople;

                String result = "Each person should pay: RM" + String.format("%.2f", amountPerPerson);
                resultTextView.setText(result);

                SharedPreferences pref = getSharedPreferences("appData", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("Equal billBreakdown", result);  // 'result' is the breakdown string
                editor.commit();

                SharedPreferences prefRead = getSharedPreferences("appData", 0 );
                String savedBreakdown = prefRead.getString("billBreakdown", "N/A");
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // This is the ID of the back button in the ActionBar
            onBackPressed(); // Go back to the parent activity
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
