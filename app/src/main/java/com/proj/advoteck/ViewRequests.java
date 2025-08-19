package com.proj.advoteck;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class ViewRequests extends Activity {

    ViewRequestsAdapter adapter;
    ListView lstView;
    ArrayList<HashMap<String, String>> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_requests);
        lstView = (ListView) findViewById(R.id.viewRequests);
        getProperties();
    }
    private void getProperties() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Config.viewRequests;
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        arraylist = new ArrayList<HashMap<String, String>>();
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                HashMap<String, String> map = new HashMap<String, String>();
                                map.put("id", jsonObject.getString("id"));
                                map.put("username", jsonObject.getString("username"));
                                map.put("info", jsonObject.getString("info"));
                                arraylist.add(map);
                            }
                            adapter = new ViewRequestsAdapter(ViewRequests.this, arraylist);
                            lstView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
        queue.add(jsonArrayRequest);
    }
}