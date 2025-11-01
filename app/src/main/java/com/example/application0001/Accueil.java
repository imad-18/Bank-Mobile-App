package com.example.application0001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application0001.adapters.TransCustomAdapter;
import com.example.application0001.database.DatabaseHelper;
import com.example.application0001.models.Transaction;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    // Array of strings...
    ListView simpleList;
    private DatabaseHelper databaseHelper; // Déclaration du Helper
    private Button mapButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);

        // NOUVELLE LIGNE : Initialisation du DatabaseHelper.
        // Ceci doit être fait avant d'appeler databaseHelper.getAllTransactions()
        databaseHelper = new DatabaseHelper(this);

        // 1. LIRE LES DONNÉES DEPUIS SQLITE
        ArrayList<Transaction> transactionList = databaseHelper.getAllTransactions();

        // Retrieve the username safely here
        String username = getIntent().getStringExtra("username");
        // Display it in the TextView
        TextView usernameText = findViewById(R.id.usernameTxt);
        if (username != null && !username.isEmpty()) {
            usernameText.setText("Bienvenue, " + username + " !");
        } else {
            usernameText.setText("Bienvenue !");
        }

        // Initialize ListView
        simpleList = findViewById(R.id.transListView);

        // 2. PASSER LA LISTE D'OBJETS À L'ADAPTATEUR
        TransCustomAdapter transCustomAdapter = new TransCustomAdapter(this, transactionList);

        simpleList.setAdapter(transCustomAdapter);

        // ✅ Handle item click
        simpleList.setOnItemClickListener((parent, view, position, id) -> {
            // You can retrieve any data from your arrays here
            String transactionName = transactionList.get(position).getName();
            double transactionPrice = transactionList.get(position).getPrice();
            String transactionDate = transactionList.get(position).getDate();

            // navigate to a new activity
            Intent intent = new Intent(Accueil.this, TransactionDetailActivity.class);
            intent.putExtra("tr_name", transactionName);
            intent.putExtra("tr_price", transactionPrice);
            intent.putExtra("op_date", transactionDate);

            startActivity(intent);
        });
        mapButton = findViewById(R.id.map_btn);
        mapButton.setOnClickListener(v -> {
            Intent intent = new Intent(Accueil.this, MapsActivity.class);
            startActivity(intent);
        });
    }
}