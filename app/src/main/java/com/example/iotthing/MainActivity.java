package com.example.iotthing;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public String X="12.9713002";
    public String Y="79.1639196";

    // creating a variable for
    // our Firebase Database.
    FirebaseDatabase firebaseDatabase;

    // creating a variable for our
    // Database Reference for Firebase.
    DatabaseReference databaseReference;
    DatabaseReference Location;


    // variable for Text view.
    private TextView retrieveTV;
    private TextView Humidity;
    private TextView Temperature;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // below line is used to get the instance
        // of our Firebase database.
        firebaseDatabase = FirebaseDatabase.getInstance();

        // below line is used to get
        // reference for our database.
        databaseReference = firebaseDatabase.getReference("Distance");
        Location = firebaseDatabase.getReference("Location");


        // initializing our object class variable.
        retrieveTV = findViewById(R.id.idTVRetrieveData);
//        Humidity = findViewById(R.id.humidity);
//        Temperature=findViewById(R.id.temps);


        // calling method
        // for getting data.
        getloc(mMap);
        getdata();

    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(Double.parseDouble(X), Double.parseDouble(Y));
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in SJT"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        float zoomLevel = 20.0f; //This goes up to 21
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));
        Circle circle = mMap.addCircle(new CircleOptions()
                .center(new LatLng(Double.parseDouble(X), Double.parseDouble(Y)))
                .radius(100)
                .strokeColor(Color.RED)
                .fillColor(Color.rgb(135,206,235)));



    }

    private void drawCircle(LatLng point,int colour){

        // Instantiating CircleOptions to draw a circle around the marker
        CircleOptions circleOptions = new CircleOptions();
        // Specifying the center of the circle
        circleOptions.center(point);
        // Radius of the circle
        circleOptions.radius(100);
        // Border color of the circle
        circleOptions.strokeColor(colour);
        // Fill color of the circle
        circleOptions.fillColor(colour);
        // Border width of the circle
        circleOptions.strokeWidth(2);
        // Adding the circle to the GoogleMap
        mMap.addCircle(circleOptions);

    }

    private void getloc(GoogleMap googleMap)
    {
        // calling add value event listener method
        // for getting the values from database.
        Location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);
                int startIndex=value.indexOf("(")+1;
                int endIndex = value.indexOf(",");
                int startIndexY=value.indexOf(",")+1;
                int endIndexY = value.indexOf(")");


                X= value.substring(startIndex,endIndex);
                Y =value.substring(startIndexY,endIndexY);
                // after getting the value we are setting
                // our value to our text view in below line.

                // Add a marker in Sydney and move the camera
                LatLng sydney = new LatLng(Double.parseDouble(X), Double.parseDouble(Y));
                mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in SJT"));
                mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
                float zoomLevel = 16.0f; //This goes up to 21
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, zoomLevel));

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String value = snapshot.getValue(String.class);
                float d = Float.parseFloat(value);
                // after getting the value we are setting
                // our value to our text view in below line.
                retrieveTV.setText(String.format("%.2f", d));

                System.out.println(String.format("%.5f", d));


                Double x = Double.parseDouble(X);
                Double y = Double.parseDouble(Y);
                System.out.println(x+" "+y);



                if(Double.parseDouble(value)<10)
                {
                    LatLng newLocation = new LatLng(x, y);
                    Marker marker = mMap.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
                    Circle circle = mMap.addCircle(new CircleOptions().center(newLocation).radius(300).strokeWidth(2).strokeColor(Color.RED).fillColor(Color.GREEN));
                    marker.setTag(circle);

// later, when the location changes
                    circle.setCenter(newLocation);
                    circle.setRadius(300);
                }
                else if(Double.parseDouble(value)<20 && Double.parseDouble(value)>10)
                {
                    LatLng newLocation = new LatLng(x, y);
                    Marker marker = mMap.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
                    Circle circle = mMap.addCircle(new CircleOptions().center(newLocation).radius(300).strokeWidth(2).strokeColor(Color.RED).fillColor(Color.YELLOW));
                    marker.setTag(circle);

// later, when the location changes
                    circle.setCenter(newLocation);
                    circle.setRadius(300);
                }
                else if(Double.parseDouble(value)<30 && Double.parseDouble(value)>20)
                {
                    LatLng newLocation = new LatLng(x, y);
                    Marker marker = mMap.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
                    Circle circle = mMap.addCircle(new CircleOptions().center(newLocation).radius(300).strokeWidth(2).strokeColor(Color.RED).fillColor(Color.BLUE));
                    marker.setTag(circle);

// later, when the location changes
                    circle.setCenter(newLocation);
                    circle.setRadius(300);
                }
                else
                {
                    LatLng newLocation = new LatLng(x, y);
                    Marker marker = mMap.addMarker(new MarkerOptions().position(newLocation).title("Current Location"));
                    Circle circle = mMap.addCircle(new CircleOptions().center(newLocation).radius(300).strokeWidth(2).strokeColor(Color.RED).fillColor(Color.RED));
                    marker.setTag(circle);

// later, when the location changes
                    circle.setCenter(newLocation);
                    circle.setRadius(300);
                }



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(MainActivity.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void anal(View view)
    {
        Intent i = new Intent(this, ml_bit.class);
        startActivity(i);
    }
}