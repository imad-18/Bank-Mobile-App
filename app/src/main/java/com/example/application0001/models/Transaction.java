package com.example.application0001.models;


import android.database.sqlite.SQLiteOpenHelper;

public class Transaction {
    private int id;
    private String name;
    private int imageResId;
    private double price;
    private String date;

    // Constructeur pour lire depuis la base de données
    public Transaction(int id, String name, int imageResId, double price, String date) {
        this.id = id;
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.date = date;
    }

    // Constructeur pour insérer de nouvelles données (sans ID)
    public Transaction(String name, int imageResId, double price, String date) {
        this.name = name;
        this.imageResId = imageResId;
        this.price = price;
        this.date = date;
    }

    // Getters (Les setters ne sont pas nécessaires si les transactions ne sont pas modifiables)
    public int getId() { return id; }
    public String getName() { return name; }
    public int getImageResId() { return imageResId; }
    public double getPrice() { return price; }
    public String getDate() { return date; }
}