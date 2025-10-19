package com.example.application0001.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.application0001.R; // Assurez-vous d'avoir le bon package R
import com.example.application0001.models.TransItem;

import java.util.ArrayList;

public class TransCustomAdapter extends BaseAdapter {

    private Context context; // Le contexte de l'Activity
    private String listTrans[]; // La liste des données
    private int listImages[];
    private double listPrices[];
    private String listDates[];
    LayoutInflater inflater;

    // 1. CONSTRUCTEUR CORRIGÉ : Stocke le contexte et la liste
    public TransCustomAdapter(Context context, String[] transList, int [] transImages, double[] transPrices, String[] transDates) {
        this.context = context;
        this.listTrans = transList;
        this.listImages = transImages;
        this.listPrices = transPrices;
        this.listDates = transDates;
        inflater = LayoutInflater.from(context);
    }

    // 2. getCount() : Doit retourner la taille de la liste pour dire à l'adaptateur combien de lignes dessiner
    @Override
    public int getCount() {
        return listTrans.length;
    }

    // 3. getItem() : Retourne l'objet à la position donnée
    @Override
    public Object getItem(int position) {
        // Retourne le nom de la transaction
        return listTrans[position];
    }

    // 4. getItemId() : Non utilisé dans la plupart des cas simples
    @Override
    public long getItemId(int position) {
        return position;
    }

    // 5. getView() : LA MÉTHODE CLÉ qui crée la vue de chaque ligne
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       /*convertView = inflater.inflate(R.layout.activity_accueil, null);
       TextView txtView = convertView.findViewById(R.id.item_name);
       ImageView imgView = convertView.findViewById(R.id.item_icon);
       txtView.setText(listTrans[position]);
       imgView.setImageResource(listImages[position]);
       return convertView;*/
        // IMPORTANT : Gonfler le layout de la CELLULE (list_item_transaction) et non celui de l'Activity (activity_accueil)
        // Si convertView est null, créez une nouvelle vue (pour l'optimisation)
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.activity_custom_list_view, parent, false);
        }

        TextView txtView = convertView.findViewById(R.id.item_name);
        ImageView imgView = convertView.findViewById(R.id.item_icon);
        TextView txtViewPrice = convertView.findViewById(R.id.item_price);
        TextView txtViewDate = convertView.findViewById(R.id.item_date);

        txtView.setText(listTrans[position]);
        imgView.setImageResource(listImages[position]);
        txtViewPrice.setText(String.valueOf(listPrices[position]));
        txtViewDate.setText(listDates[position]);

        return convertView;
    }
}
