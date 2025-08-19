package com.proj.advoteck;

import android.app.Activity;


import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class RequestForm extends Activity {

    private EditText etUsername;
    private EditText etInfo;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.request_form);

        etUsername = findViewById(R.id.etUsername);
        etInfo = findViewById(R.id.etInfo);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String info = etInfo.getText().toString().trim();

            if (username.isEmpty() || info.isEmpty()) {
                Toast.makeText(RequestForm.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                sendRequest(username, info);
            }
        });
    }

    private void sendRequest(String username, String info) {
        // Define the URL for the PHP script
        String url = Config.requestForm;  // Change this to your server's URL

        // Create a new StringRequest
        StringRequest request = new StringRequest(
                Request.Method.POST,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(RequestForm.this, "Request submitted successfully!", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RequestForm.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("username", username);
                params.put("info", info);
                return params;
            }
        };

        // Get a RequestQueue and add the request to it
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}

