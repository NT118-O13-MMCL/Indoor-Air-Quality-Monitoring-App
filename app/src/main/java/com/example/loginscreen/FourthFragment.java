package com.example.loginscreen;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import java.io.*;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FourthFragment extends Fragment {

    public FourthFragment() {
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
                activity.getSupportActionBar().setTitle("Change Information");
            }
        }
        return inflater.inflate(R.layout.fragment_fourth, container, false);
    }
}