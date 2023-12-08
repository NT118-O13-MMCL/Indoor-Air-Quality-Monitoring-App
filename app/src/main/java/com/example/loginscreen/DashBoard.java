package com.example.loginscreen;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.io.*;

public class DashBoard extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    NavigationBarView navigationBarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        navigationBarView = findViewById(R.id.bottomNavigationView);
        navigationBarView.setOnItemSelectedListener(this);
        navigationBarView.setSelectedItemId(R.id.person);
        
    }
    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();
    ThirdFragment thirdFragment = new ThirdFragment();
    FourthFragment fourthFragment = new FourthFragment();

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FirstFragment()).commit();
            return true;
        } else if (itemId == R.id.settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new SecondFragment()).commit();
            return true;
        } else if (itemId == R.id.graph) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new ThirdFragment()).commit();
            return true;
        } else if (itemId == R.id.person) {
            getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, new FourthFragment()).commit();
            return true;
        }
        return false;
    }
}