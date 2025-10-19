package com.example.application0001.models;

public class TransItem {
    private String name;
    private int iconResourceId; // L'ID de la ressource est un entier (R.drawable.xxx)

    //Constructor
    public TransItem(String name, int iconResourceId) {
        this.name = name;
        this.iconResourceId = iconResourceId;
    }

    //getters
    public String getName() {
        return name;
    }

    public int getIconResourceId() {
        return iconResourceId;
    }
}
