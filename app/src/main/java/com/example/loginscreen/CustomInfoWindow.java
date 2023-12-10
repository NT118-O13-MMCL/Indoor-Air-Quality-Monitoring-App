package com.example.loginscreen;

import android.view.View;
import android.widget.TextView;

import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.infowindow.InfoWindow;

public class CustomInfoWindow extends InfoWindow {
    public CustomInfoWindow(MapView mapView) {
        super(R.layout.bubble, mapView); // make sure the bubble.xml is correctly placed in res/layout
    }

    @Override
    public void onOpen(Object item) {

        Marker marker = (Marker)item;
        String title = marker.getTitle();
        String snippet = marker.getSnippet();

        // Now we look for the TextView in the InfoWindow layout and set its content
        View view = getView(); // This should be the view we inflated in the constructor
        TextView titleTextView = view.findViewById(R.id.title);
        TextView snippetTextView = view.findViewById(R.id.snippet);

        if (titleTextView != null && snippet != null) {
            titleTextView.setText(title); // Set the title
            snippetTextView.setText(snippet); // Set the snippet
        }
    }

    @Override
    public void onClose() {
        // Override if you want to do something when the InfoWindow is being closed
    }
}
