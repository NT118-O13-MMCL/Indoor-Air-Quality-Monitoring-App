package com.example.loginscreen;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RainFall extends AppCompatActivity {

    String[] items = {"Temperature", "Humidity", "Levels of CO2", "Particulate Matter(PM2.5)"};
    String[] items2 = {"Day", "Month", "Year"};
    String[] items3 = {"21/06/2023 01:32"};

    AutoCompleteTextView autoCompleteTxT;
    AutoCompleteTextView autoCompleteTXT2;
    AutoCompleteTextView autoCompleteTXT3;

    ArrayAdapter<String> adapterItems;
    ArrayAdapter<String> adapterItems2;
    ArrayAdapter<String> adapterItems3;

    private LineChart lineChart;
    private int colorMagentaDark = Color.argb(255, 139, 0, 139);

    private String attribute = "humidity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rain_fall);

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


        lineChart = findViewById(R.id.lineChart);
        lineChart.getDescription().setText(attribute);
        lineChart.setPinchZoom(false);
        lineChart.setScaleEnabled(false);
        lineChart.setDoubleTapToZoomEnabled(false);

        fetchDataAndDisplayChart();

        CustomMarkerView mv = new CustomMarkerView(this, R.layout.info_layout);
        lineChart.setMarker(mv);


    }

    private void fetchDataAndDisplayChart() {
        String url = "https://uiot.ixxc.dev/api/master/asset/datapoint/5zI6XqkQVSfdgOrZ1MyWEf/attribute/" + attribute;
        String token = "Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJoREkwZ2hyVlJvaE5zVy1wSXpZeDBpT2lHMzNlWjJxV21sRk4wWGE1dWkwIn0.eyJleHAiOjE3MDIyNDE2NDUsImlhdCI6MTcwMjE1NTQ2OCwiYXV0aF90aW1lIjoxNzAyMTU1MjQ1LCJqdGkiOiIwNTIzYTgxMi0yYjAyLTQ2ZTAtYWE3Mi01MjNiNzcyMWRlZjMiLCJpc3MiOiJodHRwczovL3Vpb3QuaXh4Yy5kZXYvYXV0aC9yZWFsbXMvbWFzdGVyIiwiYXVkIjoiYWNjb3VudCIsInN1YiI6IjRlM2E0NDk2LTJmMTktNDgxMy1iZjAwLTA5NDA3ZDFlZThjYiIsInR5cCI6IkJlYXJlciIsImF6cCI6Im9wZW5yZW1vdGUiLCJub25jZSI6IjU3NzM0NmUzLTE2NmEtNDM4NS04OWI4LWE3YWE1MThhM2RiMyIsInNlc3Npb25fc3RhdGUiOiJlY2QyNDU2NC1kZTEzLTRlYzUtOWRhNC1lMTNkMzg2ZjNkYTgiLCJhY3IiOiIwIiwiYWxsb3dlZC1vcmlnaW5zIjpbImh0dHBzOi8vdWlvdC5peHhjLmRldiJdLCJyZWFsbV9hY2Nlc3MiOnsicm9sZXMiOlsiZGVmYXVsdC1yb2xlcy1tYXN0ZXIiLCJvZmZsaW5lX2FjY2VzcyIsInVtYV9hdXRob3JpemF0aW9uIl19LCJyZXNvdXJjZV9hY2Nlc3MiOnsib3BlbnJlbW90ZSI6eyJyb2xlcyI6WyJyZWFkOm1hcCIsInJlYWQ6cnVsZXMiLCJyZWFkOmluc2lnaHRzIiwicmVhZDphc3NldHMiXX0sImFjY291bnQiOnsicm9sZXMiOlsibWFuYWdlLWFjY291bnQiLCJtYW5hZ2UtYWNjb3VudC1saW5rcyIsInZpZXctcHJvZmlsZSJdfX0sInNjb3BlIjoib3BlbmlkIHByb2ZpbGUgZW1haWwiLCJzaWQiOiJlY2QyNDU2NC1kZTEzLTRlYzUtOWRhNC1lMTNkMzg2ZjNkYTgiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsIm5hbWUiOiJGaXJzdCBOYW1lIExhc3QgbmFtZSIsInByZWZlcnJlZF91c2VybmFtZSI6InVzZXIiLCJnaXZlbl9uYW1lIjoiRmlyc3QgTmFtZSIsImZhbWlseV9uYW1lIjoiTGFzdCBuYW1lIiwiZW1haWwiOiJ1c2VyQGl4eGMuZGV2In0.cY7FDitg256bnWQtJRIQ5IPcr548pUf-bgsCC3aKMpaGSb83ajhYznDQwPX6rPq1glQw6UOBbRLnMtASEsrtdQpNM8CCSI0D3ukWYMGqfweDKpdNqgkOHmYdzRRwx3exhoatCz2mM83hJdLHGGAvtdXkBxEYs2H6fMS04DAD9895R-YiaW62ZDdCjoS9nAktlWBXigxzvB7wC9qOBqM3e5NSEfrOR4U1IBQJg0gKRH06V3Pt1FA45uWG_Kix2n6J_1EZt4ewe69Yy9SY3O80GlWkjQqFo0jY5IQ5dD1_b9oCmoOthe-Xpb_ZuQCpo9ff7RcY4KYg2V9XXH5kjh8i2g";
        String payload = "{\"type\":\"lttb\",\"fromTimestamp\":1701982800000,\"toTimestamp\":1702069200000,\"amountOfPoints\":100}";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .addHeader("Content-Type", "application/json")
                .addHeader("Authorization", token)
                .post(RequestBody.create(MediaType.parse("application/json"), payload))
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    try {
                        String jsonResponse = response.body().string();
                        Log.d("MainActivity", "Response received: " + jsonResponse);
                        JSONArray jsonArray = new JSONArray(jsonResponse);
                        final List<Entry> entries = new ArrayList<>();

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            long x = jsonObject.getLong("x");
                            double y = jsonObject.getDouble("y");
                            entries.add(new Entry((float) x / 1000, (float) y));
                            Log.d("MainActivity", "Entry added: x=" + x + ", y=" + y);
                        }

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LineDataSet lineDataSet = new LineDataSet(entries, "Humidity");
                                lineDataSet.setColor(colorMagentaDark);
                                lineDataSet.setFillColor(colorMagentaDark);
                                lineDataSet.setDrawValues(false);lineDataSet.setDrawValues(false);
                                lineDataSet.setDrawFilled(true);
                                lineDataSet.setLineWidth(1.5f);

                                LineData lineData = new LineData(lineDataSet);
                                lineChart.setData(lineData);
                                lineChart.invalidate();

                                List<Long> timestamps = new ArrayList<>();

                                for (Entry entry : entries) {
                                    timestamps.add((long) entry.getX() * 1000);
                                }

                                XAxis xAxis = lineChart.getXAxis();
                                xAxis.setValueFormatter(new HourAxisValueFormatter(timestamps));

                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed(); // Khi nút back được nhấn, hành động giống như việc nhấn nút back trên thiết bị
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
