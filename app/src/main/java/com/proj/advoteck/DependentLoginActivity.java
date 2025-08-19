package com.proj.advoteck;

import android.app.Activity;
import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import java.util.HashMap;
import java.util.Map;

public class DependentLoginActivity extends Activity {

    private EditText etDependentUsername;
    private EditText etDependentPassword;
    private Button btnLoginDependent;
    private TextView tvDependentRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dependentlogin);

        etDependentUsername = findViewById(R.id.etDependentUsername);
        etDependentPassword = findViewById(R.id.etDependentPassword);
        btnLoginDependent = findViewById(R.id.btnLoginDependent);
        tvDependentRegister = findViewById(R.id.tvDependentRegister);

        btnLoginDependent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginDependent();
            }
        });

        tvDependentRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to Dependent registration activity
                // Replace with your registration activity
                startActivity(new Intent(DependentLoginActivity.this, RegisterDependentActivity.class));
            }
        });
    }

    private void loginDependent() {
        String username = etDependentUsername.getText().toString();
        String password = etDependentPassword.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please enter both username and password", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = Config.loginDependent; // Change URL to your actual backend endpoint

        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if (response.equals("Login successful")) {
                            Toast.makeText(DependentLoginActivity.this, "Login successful!", Toast.LENGTH_SHORT).show();
                            // Navigate to another activity upon successful login
                            startActivity(new Intent(DependentLoginActivity.this, RequestForm.class)); // Replace with your activity
                        } else {
                            Toast.makeText(DependentLoginActivity.this, "Login failed: " + response, Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(DependentLoginActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        queue.add(stringRequest);
    }
}

