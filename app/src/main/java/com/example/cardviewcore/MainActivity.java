package com.example.cardviewcore;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.cardviewcore.Adapter.QuestionsListAdapter;
import com.example.cardviewcore.Model.Question;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerview;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // invoke recyclerview
        recyclerview =(RecyclerView)findViewById(R.id.questionsList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(linearLayoutManager);

        GsonBuilder gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();

        doRequest();

    }

    private void doRequest()
    {
        final String ENDPOINT = "http://192.168.1.3/questions.json";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        final StringRequest request = new StringRequest(Request.Method.GET, ENDPOINT, onSuccess, onFail);
        requestQueue.add(request);
    }

    private Response.Listener<String> onSuccess = new Response.Listener<String>() {
        @Override
        public void onResponse(String response)
        {
            List<Question> questions = Arrays.asList(gson.fromJson(response, Question[].class));
            initializeAdapter(questions);
        }
    };

    private void initializeAdapter(List<Question> questions)
    {
        QuestionsListAdapter adapter = new QuestionsListAdapter(questions, this);
        recyclerview.setAdapter(adapter);
    }

    private final Response.ErrorListener onFail = new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error)
        {
            Log.e("MainActivity", error.toString());
        }
    };
}
