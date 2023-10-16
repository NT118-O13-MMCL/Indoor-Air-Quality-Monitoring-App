package com.example.first_homework;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private Button button_next;

    private Button button_dial;

    EditText edit_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edit_text = (EditText)findViewById(R.id.editTextText);
        button_next = (Button)findViewById(R.id.button);
        button_dial = (Button)findViewById(R.id.dial_button);

        button_next.setOnClickListener(view -> {
            String text = edit_text.getText().toString();
            Intent intent = new Intent(Intent.ACTION_PROCESS_TEXT)
                    .setType("text/plain")
                    .putExtra(Intent.EXTRA_TEXT, text);
            startActivity(intent);
        });

        button_dial.setOnClickListener(view -> {
            Intent intent = new Intent(Intent.ACTION_DIAL)
                    .setData(Uri.parse("tel:0912456789"));
            startActivity(intent);
        });
    }
}