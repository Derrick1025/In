package com.example.Individual_Assignment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Split Bill");

        Button equalButton = findViewById(R.id.equal_button);
        Button customPercButton = findViewById(R.id.custom_perc_button);
        Button customAmountButton = findViewById(R.id.custom_amount_button);

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EqualBreakdownActivity.class);
                startActivity(intent);
            }
        });

        customPercButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomBreakdownActivity.class);
                startActivity(intent);
            }
        });

        customAmountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CustomBreakdownAmountActivity.class);
                startActivity(intent);
            }
        });
    }
}
