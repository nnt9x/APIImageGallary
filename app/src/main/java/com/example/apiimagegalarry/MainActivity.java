package com.example.apiimagegalarry;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<String> urlAnimals = new ArrayList<>();
    ListView lvDemo;

    AnimalAdapter animalAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvDemo = findViewById(R.id.lvDemo);

        // Tao Adapter
        animalAdapter = new AnimalAdapter(MainActivity.this, urlAnimals );

        // Lay du lieu
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://shibe.online/api/shibes?count=5";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // Du lieu tai day
                Log.d("API Animal", response.toString());
                // Duyet qua json array => moi lan duyet -> lay du lieu url Image
                try{
                    for(int i = 0; i < response.length(); i++){
                        urlAnimals.add(response.get(i).toString());
                        animalAdapter.notifyDataSetChanged();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "API Error: "+ error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jsonArrayRequest);
        // Da co list du lieu

        Log.d("Data", urlAnimals.toString());



        // Set Adapter cho listview
        lvDemo.setAdapter(animalAdapter);

    }
}