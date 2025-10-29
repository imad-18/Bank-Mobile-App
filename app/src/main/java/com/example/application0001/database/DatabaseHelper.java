package com.example.application0001.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.application0001.R;
import com.example.application0001.models.Transaction;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bank_data.db";
    private static final int DATABASE_VERSION = 1;

    // Noms des tables et colonnes (pour la clarté)
    public static final String TABLE_TRANSACTIONS = "transactions";
    public static final String COL_ID = "id";
    public static final String COL_NAME = "name";
    public static final String COL_IMAGE_RES_ID = "image_res_id";
    public static final String COL_PRICE = "price";
    public static final String COL_DATE = "date";

    // SQL pour créer la table
    private static final String CREATE_TABLE_TRANSACTIONS =
            "CREATE TABLE " + TABLE_TRANSACTIONS + " (" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_NAME + " TEXT, " +
                    COL_IMAGE_RES_ID + " INTEGER, " +
                    COL_PRICE + " REAL, " +
                    COL_DATE + " TEXT" +
                    ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Créer la table la première fois que la base de données est créée
        db.execSQL(CREATE_TABLE_TRANSACTIONS);
        // Insérer les données initiales
        seedDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSACTIONS);
        onCreate(db);
    }

    // Fonction pour insérer vos données initiales dans la base de données
    private void seedDatabase(SQLiteDatabase db) {
        // Ces données doivent être insérées par un ContentValue
        Transaction[] initialTransactions = {
                new Transaction("Facture Internet", R.drawable.phonecall, 299.00, "09/10/25"),
                new Transaction("Emission d'un", R.drawable.share, 5000.0, "19/10/25"),
                new Transaction("Paiement d'un", R.drawable.percent, 2990.0, "22/10/25"),
                new Transaction("Paiement par carte", R.drawable.card, 500.00, "24/09/25"),
                new Transaction("Retrait d'espèces", R.drawable.dollarbill, 1000.0, "30/10/25")
        };

        for (Transaction tr : initialTransactions) {
            ContentValues values = new ContentValues();
            values.put(COL_NAME, tr.getName());
            values.put(COL_IMAGE_RES_ID, tr.getImageResId());
            values.put(COL_PRICE, tr.getPrice());
            values.put(COL_DATE, tr.getDate());

            db.insert(TABLE_TRANSACTIONS, null, values);
        }
    }

    /**
     * Méthode pour lire toutes les transactions depuis la BD
     */
    public ArrayList<Transaction> getAllTransactions() {
        ArrayList<Transaction> transactionList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Requête SQL pour obtenir toutes les lignes
        String selectQuery = "SELECT * FROM " + TABLE_TRANSACTIONS;
        android.database.Cursor cursor = db.rawQuery(selectQuery, null);

        // Boucler à travers toutes les lignes et ajouter à la liste
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME));
                int imageResId = cursor.getInt(cursor.getColumnIndexOrThrow(COL_IMAGE_RES_ID));
                double price = cursor.getDouble(cursor.getColumnIndexOrThrow(COL_PRICE));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COL_DATE));

                Transaction transaction = new Transaction(id, name, imageResId, price, date);
                transactionList.add(transaction);
            } while (cursor.moveToNext());
        }

        // Fermer le curseur et la connexion
        cursor.close();
        db.close();

        return transactionList;
    }
}