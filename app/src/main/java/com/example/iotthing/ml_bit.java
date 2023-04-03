package com.example.iotthing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

        import androidx.appcompat.app.AppCompatActivity;
        import androidx.recyclerview.widget.LinearLayoutManager;
        import androidx.recyclerview.widget.RecyclerView;
        import android.os.Bundle;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ml_bit extends AppCompatActivity {

    private RecyclerView recyclerView;
    SensorListAdapter
            adapter; // Create Object of the Adapter class
    DatabaseReference mbase;
    DatabaseReference ubase;// Create object of the
    // Firebase Realtime Database
    Sensor Sensor = new Sensor();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ml_bit);

        // Create a instance of the database and get
        // its reference
        mbase
                = FirebaseDatabase.getInstance().getReference("Nodes");
        ubase
                = FirebaseDatabase.getInstance().getReference("Sensor1");

        recyclerView = findViewById(R.id.recycler1);

        // To display the Recycler view linearly
        recyclerView.setLayoutManager(
                new LinearLayoutManager(this));

        // It is a class provide by the FirebaseUI to make a
        // query in the database to fetch appropriate data
        FirebaseRecyclerOptions<Sensor> options
                = new FirebaseRecyclerOptions.Builder<Sensor>()
                .setQuery(mbase, Sensor.class)
                .build();
        // Connecting object of required Adapter class to
        // the Adapter class itself
        adapter = new SensorListAdapter(options);
        // Connecting Adapter class with the Recycler view*/
        recyclerView.setAdapter(adapter);


    }

    // Function to tell the app to start getting
    // data from database on starting of the activity
    @Override protected void onStart()
    {
        super.onStart();
        adapter.startListening();
        getdata();
    }

    // Function to tell the app to stop getting
    // data from database on stopping of the activity
    @Override protected void onStop()
    {
        super.onStop();
        adapter.stopListening();
    }

    private void getdata() {

        // calling add value event listener method
        // for getting the values from database.
        mbase.child("Node1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // this method is call to get the realtime
                // updates in the data.
                // this method is called when the data is
                // changed in our Firebase console.
                // below line is for getting the data from
                // snapshot of our database.
                String id = ubase.push().getKey();
                String distance = snapshot.child("Distance").getValue(String.class);
                String humidity = snapshot.child("humidity").getValue(String.class);
                String temperature = snapshot.child("temperature").getValue(String.class);
                String X =snapshot.child("X").getValue(String.class);
                String Y =snapshot.child("Y").getValue(String.class);
                String time = snapshot.child("time").getValue(String.class);
                Sensor.Sensor(time,id,distance,humidity,temperature,X,Y);
                assert id != null;
                ubase.child(id).setValue(Sensor);
                Toast.makeText(ml_bit.this,"Data added",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // calling on cancelled method when we receive
                // any error or we are not able to get the data.
                Toast.makeText(ml_bit.this, "Fail to get data.", Toast.LENGTH_SHORT).show();
            }
        });
    }

}