package com.example.listycity03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AddEditCityFragment.Listener {

    private ArrayList<City> cities;
    private ArrayList<String> displayList;
    private ArrayAdapter<String> adapter;
    private ListView cityList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);

        // starter cities (name + province)
        cities = new ArrayList<>();
        cities.add(new City("Edmonton", "AB"));
        cities.add(new City("Vancouver", "BC"));
        cities.add(new City("Toronto", "ON"));
        cities.add(new City("Hamilton", "ON"));

        // build display strings
        displayList = new ArrayList<>();
        rebuildDisplayList();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, displayList);
        cityList.setAdapter(adapter);

        // REQUIRED: tap to edit existing city
        cityList.setOnItemClickListener((parent, view, position, id) -> {
            City clickedCity = cities.get(position);
            AddEditCityFragment.newInstance(clickedCity)
                    .show(getSupportFragmentManager(), "EDIT_CITY");
        });
    }

    private void rebuildDisplayList() {
        displayList.clear();
        for (City c : cities) {
            displayList.add(c.getName() + "   " + c.getProvince());
        }
    }

    @Override
    public void onCityEdited() {
        rebuildDisplayList();
        adapter.notifyDataSetChanged();
    }
}
