package com.Pedrum.StoryFinderFinale.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;

import com.Pedrum.StoryFinderFinale.R;
import com.Pedrum.StoryFinderFinale.adapters.EventListAdapter;
import com.Pedrum.StoryFinderFinale.models.Event;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;


public class StoryListActivity extends AppCompatActivity {

    public static final String API_URL = "https://api.meetup.com/";
    SearchView searchView;
    RecyclerView recyclerView;
    EventListAdapter adapter;
    ArrayList<Event> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
/*
        Map<String, String> params = new HashMap<String, String>();

        params.put("key", "6686238527170417b2d1454208b39");
        CustomRequest jsObjRequest = new CustomRequest(API_URL + "find/events?key=6686238527170417b2d1454208b39", params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });*/


        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        searchView = (SearchView) findViewById(R.id.search_bar);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.e("Tagmond", "onQueryTextChange: on submit");
                if (adapter != null) {
                    searchInStories(query, list);
                    adapter.notifyDataSetChanged();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (adapter != null) {
                    searchInStories(newText, list);
                    adapter.notifyDataSetChanged();
                }
                Log.e("Tagmond", "onQueryTextChange: on change");
                return true;
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new EventListAdapter(this, list);
        recyclerView.setAdapter(adapter);
        StringRequest stringRequest = new StringRequest(API_URL + "find/events?key=6686238527170417b2d1454208b39", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Log.i("TAG_SERVER", "onResponse: " + response);
                Gson gson = new Gson();
                ArrayList list2 = gson.fromJson(response, new TypeToken<List<Event>>() {

                }.getType());
                list.addAll(list2);
                adapter.notifyDataSetChanged();
                Log.i("TAG", "onResponse: " + list.size()
                );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        requestQueue.add(stringRequest);

    }

    public ArrayList<Event> searchInStories(String query, ArrayList<Event> userList) {

        ArrayList<Event> list = new ArrayList<>();
        if (!query.equals("")) {
            for (Event user : userList) {
                if (user.getName().contains(query))
                    list.add(user);
            }
            return list;
        } else {
            return userList;
        }
    }
}
