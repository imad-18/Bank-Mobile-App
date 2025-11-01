package com.example.application0001;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.application0001.models.Agency;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.application0001.databinding.ActivityMapsBinding;

import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnInfoWindowClickListener {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    private List<Agency> agencies = new ArrayList<>(); // Liste des agences stroed statically
    private Agency selectedAgency;

    private SearchView searchView;
    private Button callAgenceButton, smsButton, emailButton;

    // Numéros codés en dur
    private final String CALL_CENTER_NUMBER = "+212578957489";
    private final String SMS_NUMBER = "+212648364140";
    private final String EMAIL_ADDRESS = "supportService@banky.ma";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initViews();
        // AJOUT : Remplir la liste des agences AVANT d'initialiser la carte
        setupAgencies();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        setupListeners();
    }

    private void initViews() {
        searchView = findViewById(R.id.searchBar);
        callAgenceButton = findViewById(R.id.callAgence_btn);
        smsButton = findViewById(R.id.sms_btn);
        emailButton = findViewById(R.id.email_btn);
    }

    private void setupListeners() {
        // ✅ Correct listener for SearchView
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchAgency(query);
                return true; // consume the event
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // Optional: live search
                return false;
            }
        });
        callAgenceButton.setOnClickListener(v -> callCenter());
        smsButton.setOnClickListener(v -> sendSMS());
        emailButton.setOnClickListener(v -> sendEmail());
    }

    private void setupAgencies() {
        agencies.add(new Agency("Agence Centre Ville", "123 Avenue Hassan II, Casablanca", "Mohamed Alami", "+212522111111", 33.5731, -7.5898));
        agencies.add(new Agency("Agence Maarif", "45 Rue Mohammed V, Maarif", "Fatima Zahra", "+212522111112", 33.5682, -7.6324));
        agencies.add(new Agency("Agence Ain Diab", "Corniche Ain Diab, Casablanca", "Karim Bennani", "+212522111113", 33.5912, -7.6874));
        agencies.add(new Agency("Agence Racine", "12 Boulevard Rachidi, Casablanca", "Hassan El Fassi", "+212522111114", 33.5865, -7.6142));

    }

    /*@Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }*/
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Centrer sur Casablanca
        LatLng casablanca = new LatLng(33.5731, -7.5898);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(casablanca, 12));

        // Ajouter les marqueurs des agences
        addAgenciesToMap();

        // Configurer le clic sur les info windows
        mMap.setOnInfoWindowClickListener(this);

        // Demander la permission de localisation
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }
    }

    private void addAgenciesToMap() {
        for (Agency agency : agencies) {
            LatLng location = new LatLng(agency.getLatitude(), agency.getLongitude());

            Marker marker = mMap.addMarker(new MarkerOptions()
                    .position(location)
                    .title(agency.getName())
                    .snippet("Cliquez pour plus d'infos")
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

            // Stocker l'agence dans le tag du marqueur
            if (marker != null) {
                marker.setTag(agency);
            }
        }
    }

    private void searchAgency(String query) {
        query = query.toLowerCase().trim();
        if (query.isEmpty()) {
            Toast.makeText(this, "Entrer un nom d'agence", Toast.LENGTH_SHORT).show();
            return;
        }

        for (Agency agency : agencies) {
            if (agency.getName().toLowerCase().contains(query)) {
                LatLng location = new LatLng(agency.getLatitude(), agency.getLongitude());
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));

                selectedAgency = agency;
                Toast.makeText(this, "Agence trouvée: " + agency.getName(), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        Toast.makeText(this, "Aucune agence trouvée", Toast.LENGTH_SHORT).show();
    }


    // Implémentation unique et correcte de onInfoWindowClick
    @Override
    public void onInfoWindowClick(Marker marker) {
        Agency agency = (Agency) marker.getTag();
        if (agency != null) {
            // ESSENTIEL : Définir l'agence sélectionnée
            selectedAgency = agency;

            // Afficher les détails de l'agence (en utilisant une boîte de dialogue pour la clarté)
            String details = "Adresse: " + agency.getAddress() + "\n" +
                    "Gérant: " + agency.getManager() + "\n" +
                    "Téléphone: " + agency.getPhone();

            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this);
            builder.setTitle(agency.getName());
            builder.setMessage(details);
            builder.setPositiveButton("OK", null);
            builder.show();
        }
        // L'ancienne méthode showAgencyDetails() est supprimée car sa logique est ici.
    }

    // AVANT : private void callCenter() { ... intent.setData(Uri.parse("tel:" + CALL_CENTER_NUMBER)); ... }
    private void callCenter() {
        // Vérifier si une agence est sélectionnée
        if (selectedAgency == null) {
            Toast.makeText(this, "Veuillez sélectionner une agence d'abord.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Utiliser le numéro de l'agence sélectionnée
        String phoneNumber = selectedAgency.getPhone();

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber)); // Utilisation de selectedAgency.getPhone()

        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
                == PackageManager.PERMISSION_GRANTED) {
            startActivity(intent);
        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.CALL_PHONE},
                    2);
        }
    }
    private void sendSMS() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("sms:" + SMS_NUMBER));
        intent.putExtra("sms_body", "Bonjour, je souhaite obtenir des informations sur vos services.");
        startActivity(intent);
    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:" + EMAIL_ADDRESS));
        intent.putExtra(Intent.EXTRA_SUBJECT, "Réclamation - BankApp");
        intent.putExtra(Intent.EXTRA_TEXT, "Bonjour,\n\nJe souhaite porter à votre attention la réclamation suivante :\n\n\nCordialement.");

        try {
            startActivity(Intent.createChooser(intent, "Envoyer un email via"));
        } catch (Exception e) {
            Toast.makeText(this, "Aucune application email trouvée", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED && mMap != null) {
                mMap.setMyLocationEnabled(true);
            }
        }
    }
}