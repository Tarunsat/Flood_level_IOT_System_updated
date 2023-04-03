package com.example.iotthing;

import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import androidx.annotation.NonNull;
        import androidx.recyclerview.widget.RecyclerView;
        import com.firebase.ui.database.FirebaseRecyclerAdapter;
        import com.firebase.ui.database.FirebaseRecyclerOptions;

// FirebaseRecyclerAdapter is a class provided by
// FirebaseUI. it provides functions to bind, adapt and show
// database contents in a Recycler View
public class SensorListAdapter extends FirebaseRecyclerAdapter<
        Sensor, SensorListAdapter.personsViewholder> {

    public SensorListAdapter(
            @NonNull FirebaseRecyclerOptions<Sensor> options)
    {
        super(options);
    }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull personsViewholder holder,
                     int position, @NonNull Sensor model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.distance.setText((model.getDistance()));

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.temperature.setText((model.getTemperature()));

        // Add age from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.humidity.setText((model.getHumidity()));
        holder.X.setText((model.getX()));
        holder.Y.setText((model.getY()));
    }

    // Function to tell the class about the Card view (here
    // "person.xml")in
    // which the data will be shown
    @NonNull
    @Override
    public personsViewholder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sensors, parent, false);
        return new SensorListAdapter.personsViewholder(view);
    }

    // Sub Class to create references of the views in Crad
    // view (here "person.xml")
    class personsViewholder
            extends RecyclerView.ViewHolder {
        TextView distance, humidity, temperature, X, Y;
        public personsViewholder(@NonNull View itemView)
        {
            super(itemView);

            distance
                    = itemView.findViewById(R.id.waterlevel);
            humidity = itemView.findViewById(R.id.humidity);
            temperature = itemView.findViewById(R.id.temperature);
            X = itemView.findViewById(R.id.X);
            Y = itemView.findViewById(R.id.Y);
        }
    }
}