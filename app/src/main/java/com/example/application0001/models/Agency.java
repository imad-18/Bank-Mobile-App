package com.example.application0001.models;

public class Agency {
    private String name;
    private String address;
    private String manager;
    private String phone;
    private double latitude;
    private double longitude;
    public Agency(String name, String address, String manager, String phone, double latitude, double longitude) {
        this.name = name;
        this.address = address;
        this.manager = manager;
        this.phone = phone;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getManager() {
        return manager;
    }
    public String getPhone() {
        return phone;
    }
    public double getLatitude() {
        return latitude;
    }
    public double getLongitude() {
        return longitude;
    }
}