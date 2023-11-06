package com.example.loginscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView to_signup = findViewById(R.id.to_signup);
        Button btn_back = findViewById(R.id.btn_back);
        Button btn_login = findViewById(R.id.btn_login);
        EditText editText_username = findViewById(R.id.username);
        EditText editText_password = findViewById(R.id.password);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editText_username.getText().toString();
                String password = editText_password.getText().toString();

                String urlToken = "https://uiot.ixxc.dev/auth/realms/master/protocol/openid-connect/token";
                String urlUser = "https://uiot.ixxc.dev/api/master/user/user";

                OkHttpClient client = new OkHttpClient();

                RequestBody data = new FormBody.Builder()
                        .add("client_id", "openremote")
                        .add("username", username)
                        .add("password", password)
                        .add("grant_type", "password")
                        .build();



                Request request = new Request.Builder()
                        .url(urlToken)
                        .post(data)
                        .build();
                Call call = client.newCall(request);

                call.enqueue(new Callback() {
                    public void onResponse(Call call, Response response)
                            throws IOException {

                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);
                        if (jsonObject != null) {
                            if (jsonObject.has("access_token")) {
                                String accessToken = jsonObject.get("access_token").getAsString();

                                OkHttpClient secondClient = new OkHttpClient();
                                Request userRequest = new Request.Builder()
                                        .url(urlUser)
                                        .header("Accept", "application/json")
                                        .addHeader("Authorization", "Bearer " + accessToken)
                                        .build();

                                Call secondCall = secondClient.newCall(userRequest);

                                secondCall.enqueue(new Callback() {
                                    public void onResponse(Call call, Response response)
                                            throws IOException {
                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(MainActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    public void onFailure(Call call, IOException e) {

                                        runOnUiThread(new Runnable() {
                                            public void run() {
                                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                    }
                                });
                            }
                            else {
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(MainActivity.this, jsonObject.get("error_description").getAsString(), Toast.LENGTH_SHORT).show();
                                    }
                                });

                            };

                        };
                    }

                    public void onFailure(Call call, IOException e) {
                        runOnUiThread(new Runnable() {
                            public void run() {
                                Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });
            }
        });

        to_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignUp_Screen.class);
                startActivity(intent);
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomePage_Screen.class);
                startActivity(intent);
            }
        });
    }
}
