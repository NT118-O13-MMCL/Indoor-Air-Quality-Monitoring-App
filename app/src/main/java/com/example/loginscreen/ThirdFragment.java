package com.example.loginscreen;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ThirdFragment extends Fragment {


    public ThirdFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();

            // Đặt tiêu đề cho ActionBar
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setTitle("Chart");
            }
        }

        View view = inflater.inflate(R.layout.fragment_third, container, false);
        return view;
    }
}