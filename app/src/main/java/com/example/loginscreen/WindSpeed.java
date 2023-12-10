package com.example.loginscreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

public class WindSpeed extends AppCompatActivity {

    String[] items = {"Temperature", "Humidity", "Levels of CO2", "Particulate Matter(PM2.5)"};
    String[] items2 = {"Day", "Month", "Year"};
    String[] items3 = {"21/06/2023 01:32"};

    AutoCompleteTextView autoCompleteTxT;
    AutoCompleteTextView autoCompleteTXT2;
    AutoCompleteTextView autoCompleteTXT3;

    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wind_speed);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        autoCompleteTxT = findViewById(R.id.auto_complete_txt);
        autoCompleteTXT2 = findViewById(R.id.auto_complete_txt2);
        autoCompleteTXT3 = findViewById(R.id.auto_complete_txt3);

        adapterItems = new ArrayAdapter<>(this, R.layout.list_item, items);
        adapterItems2 = new ArrayAdapter<>(this, R.layout.list_item, items2);
        adapterItems3 = new ArrayAdapter<>(this, R.layout.list_item, items3);

        autoCompleteTxT.setAdapter(adapterItems);
        autoCompleteTXT2.setAdapter(adapterItems2);
        autoCompleteTXT3.setAdapter(adapterItems3);

        autoCompleteTxT.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTXT2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });

        autoCompleteTXT3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Item: " + item, Toast.LENGTH_SHORT).show();
            }
        });
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}