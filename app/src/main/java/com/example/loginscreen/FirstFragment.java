package com.example.loginscreen;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FirstFragment extends Fragment {

    public FirstFragment() {
        // Required empty public constructor
    }

    CardView rainfall;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_first, container, false);

        rainfall = view.findViewById(R.id.rainfall);
        rainfall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), RainFall.class);
                startActivity(intent);
            }
        });


        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();

            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle("Home");
            }
        }
        return view;
    }
}