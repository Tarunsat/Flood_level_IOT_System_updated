package com.example.iotthing;

public class Sensor {
    private String Distance;

    // Variable to store data corresponding
    // to lastname keyword in database
    private String humidity;
    private String time;

    // Variable to store data corresponding
    // to age keyword in database
    private String temperature;

    private String X;

    private String Y;
    private String id;

    // Mandatory empty constructor
    // for use of FirebaseUI
    public Sensor() {
    }

    // Getter and setter method
    public String getDistance() {
        return Distance;
    }

    public String getTime() {
        return time;
    }

    public void setDistance(String Distance) {
        this.Distance = Distance;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getX() {
        return X;
    }

    public void setX(String X) {
        this.X = X;
    }

    public String getY() {
        return Y;
    }

    public void Y(String Y) {
        this.Y = Y;
    }

    public void Sensor(String time, String id, String Distance, String humidity, String temperature, String X, String Y) {
        this.Distance = Distance;
        this.time = time;
        this.humidity = humidity;
        this.temperature = temperature;
        this.X = X;
        this.Y = Y;
        this.id = id;

    }
}
