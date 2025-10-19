package com.example.application0001;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class TransactionDetailActivity extends AppCompatActivity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction_detail);


        // Retrieve the username safely here
        String trName = getIntent().getStringExtra("tr_name");
        double trPrice = getIntent().getDoubleExtra("tr_price", 0.0);
        String opDate = getIntent().getStringExtra("op_date");

        // Display it in the TextView
        TextView transDesc = findViewById(R.id.description);
        TextView transPrice = findViewById(R.id.montant);
        TextView transDate = findViewById(R.id.date_op);

        /*if (trName != null && !trName.isEmpty()) {
            dateOperation.setText(trName);
        }*/

        transPrice.setText("Montant \n" + trPrice + " MAD");

        transDate.setText("Date Opération \n"+opDate);

        transDesc.setText("Description \n"+trName);
    }
}
