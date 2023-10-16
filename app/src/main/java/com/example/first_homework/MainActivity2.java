package com.example.first_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    private Button button_back;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tv = (TextView)findViewById(R.id.textView2);
        button_back = (Button) findViewById(R.id.button);

        Intent intent = getIntent();
        String text = intent.getStringExtra(Intent.EXTRA_TEXT);
        tv.setText(text);

        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openActivity_main();
            }
        });


    }
    public void openActivity_main()
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}