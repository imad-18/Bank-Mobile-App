package com.example.application0001.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application0001.R; // Assurez-vous d'avoir le bon package R
import com.example.application0001.models.Transaction;

import java.util.ArrayList;

public class TransCustomAdapter extends BaseAdapter {

    private Context context; // Le contexte de l'Activity
    private ArrayList<Transaction> transactionList; // CHANGEMENT : Une seule liste d'objets
    LayoutInflater inflater;

    // 1. CONSTRUCTEUR CORRIGÉ : Stocke le contexte et la liste
    public TransCustomAdapter(Context context, ArrayList<Transaction> transactionList) {
        this.context = context;
        this.transactionList = transactionList;
        inflater = LayoutInflater.from(context);
    }

    // 2. getCount() : Doit retourner la taille de la liste pour dire à l'adaptateur combien de lignes dessiner
    @Override
    public int getCount() {
        return transactionList.size(); // Utilise la taille de la liste d'objets
    }

    // 3. getItem() : Retourne l'objet à la position donnée
    @Override
    public Object getItem(int position) {
        return transactionList.get(position); // Retourne l'objet Transaction
    }

    // 4. getItemId() : Non utilisé dans la plupart des cas simples
    @Override
    public long getItemId(int position) {
        // Optionnel : peut retourner l'ID de la BD
        return transactionList.get(position).getId();
    }

    // 5. getView() : LA MÉTHODE CLÉ qui crée la vue de chaque ligne
    // getView() : Utilisation des Getters de l'objet Transaction
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // IMPORTANT : Gonfler le layout de la CELLULE (list_item_transaction) et non celui de l'Activity (activity_accueil)
        // Si convertView est null, créez une nouvelle vue (pour l'optimisation)

        // Récupérer l'objet pour la ligne actuelle
        Transaction currentTransaction = transactionList.get(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_view, parent, false);
        }

        // Références des vues (Note : Les IDs doivent exister dans R.layout.list_item_transaction !)
        TextView txtView = convertView.findViewById(R.id.item_name);
        ImageView imgView = convertView.findViewById(R.id.item_icon);
        TextView txtViewPrice = convertView.findViewById(R.id.item_price);
        TextView txtViewDate = convertView.findViewById(R.id.item_date);

        // REMPLISSAGE DES DONNÉES PAR GETTER
        txtView.setText(currentTransaction.getName());
        imgView.setImageResource(currentTransaction.getImageResId());
        txtViewPrice.setText(String.valueOf(currentTransaction.getPrice()));
        txtViewDate.setText(currentTransaction.getDate());

        return convertView;
    }
}
