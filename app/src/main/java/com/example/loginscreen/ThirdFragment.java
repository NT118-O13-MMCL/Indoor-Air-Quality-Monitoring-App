package com.example.loginscreen;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

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
                activity.getSupportActionBar().setTitle("Map");
            }
        }

        View view = inflater.inflate(R.layout.fragment_third, container, false);

        Configuration.getInstance().load(view.getContext(),
                getActivity().getSharedPreferences("osmdroid", getContext().MODE_PRIVATE));

        // Sử dụng view.findViewById() để tìm kiếm trong layout của Fragment
        MapView mapView = view.findViewById(R.id.mapView);

        mapView.setTileSource(org.osmdroid.tileprovider.tilesource.TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);


        GeoPoint UITPoint = new GeoPoint(10.87010241486628, 106.80306583314845);
        GeoPoint EPoint = new GeoPoint(10.869795551725913, 106.80258404109576);



        mapView.getController().setZoom(20);
        mapView.getController().setCenter(UITPoint);

        mapView.setMinZoomLevel(5.0);
        mapView.setMaxZoomLevel(19.8);


        Drawable customMarker = getResources().getDrawable(R.mipmap.asset_foreground);
        customMarker.setBounds(0, 0, customMarker.getIntrinsicWidth(), customMarker.getIntrinsicHeight());

        Marker startMarker = new Marker(mapView);
        startMarker.setPosition(EPoint);
        startMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        startMarker.setIcon(customMarker);

        startMarker.setTitle("Solar De Rotterdam");
        startMarker.setSnippet("Panel orientation: EAST WEST\nSystem efficiency (%): 93\nInstalled capacity (kW): 16.52");

        startMarker.setInfoWindow(new CustomInfoWindow(mapView));
        startMarker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                // Check if the info window is already showing
                if (marker.isInfoWindowShown()) {
                    // It's shown, so hide it
                    marker.closeInfoWindow();
                } else {
                    // It's not shown, so show it
                    marker.showInfoWindow();
                }
                return true;
            }
        });

        mapView.getOverlays().add(startMarker);

        return view;
    }
}