package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class HomePage_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page_screen);

        TextView loginhomepage = findViewById(R.id.loginhomepage);
        TextView signuphomepage = findViewById(R.id.signuphomepage);

        loginhomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage_Screen.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signuphomepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePage_Screen.this, SignUp_Screen.class);
                startActivity(intent);
            }
        });
    }
}