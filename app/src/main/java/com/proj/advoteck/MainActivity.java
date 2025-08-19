package com.proj.advoteck;

// MainActivity.java

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private Button btnDependent, btnCaregiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize buttons
        btnDependent = findViewById(R.id.btnDependent);
        btnCaregiver = findViewById(R.id.btnCaregiver);

        // Set click listeners for buttons
        btnDependent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDependentLogin();
            }
        });

        btnCaregiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCaregiverLogin();
            }
        });
    }

    // Method to open Dependent Login activity
    private void openDependentLogin() {
        Intent intent = new Intent(MainActivity.this, DependentLoginActivity.class);
        startActivity(intent);
    }

    // Method to open Caregiver Login activity
    private void openCaregiverLogin() {
        Intent intent = new Intent(MainActivity.this, CaregiverLoginActivity.class);
        startActivity(intent);
    }
}

