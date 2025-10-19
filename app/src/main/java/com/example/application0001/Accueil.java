package com.example.application0001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.application0001.adapters.TransCustomAdapter;
import com.example.application0001.models.TransItem;

import java.util.ArrayList;

public class Accueil extends AppCompatActivity {

    // Array of strings...
    ListView simpleList;
    String transactionsList[] = {"Facture Internet", "Emission d'un", "Paiement d'un", "Paiement par carte", "Retrait d'espèces"};
    int transImages[] = {R.drawable.phonecall, R.drawable.share, R.drawable.percent, R.drawable.card, R.drawable.dollarbill};
    double transPrices[] = {299.00, 5000.0, 2990.0, 500.00, 1000.0};
    String transDates[] = {"09/10/25", "19/10/25", "22/10/25", "24/09/25", "30/10/25"};

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_accueil);


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

        TransCustomAdapter transCustomAdapter = new TransCustomAdapter(
                this,
                transactionsList,
                transImages,
                transPrices,
                transDates);

        simpleList.setAdapter(transCustomAdapter);

        // ✅ Handle item click
        simpleList.setOnItemClickListener((parent, view, position, id) -> {
            // You can retrieve any data from your arrays here
            String transactionName = transactionsList[position];
            double transactionPrice = transPrices[position];
            String transactionDate = transDates[position];

            // Example: navigate to a new activity
            Intent intent = new Intent(Accueil.this, TransactionDetailActivity.class);
            intent.putExtra("tr_name", transactionName);
            intent.putExtra("tr_price", transactionPrice);
            intent.putExtra("op_date", transactionDate);

            startActivity(intent);
        });
    }
}