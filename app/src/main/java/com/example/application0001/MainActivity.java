package com.example.application0001;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    // Déclaration des vues
    private TextInputEditText usernameEditText;
    private TextInputEditText passwordEditText;
    private Button loginButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 1. Initialisation des vues (Liaison des ID XML aux variables Java)
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);

        // 2. Configuration de l'écouteur de clic pour le bouton de connexion
        loginButton.setOnClickListener(v -> {
            // Appel de la fonction de validation
            validateLoginFields();
        });

        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });*/
    }

    /**
     * Valide les champs de saisie Nom d'utilisateur et Mot de passe.
     */
    private void validateLoginFields() {
        // Récupérer le texte des champs et supprimer les espaces de début/fin
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        // Indicateur pour savoir si une erreur a été trouvée
        boolean hasError = false;

        // 3. Validation du Nom d'utilisateur
        if (username.isEmpty()) {
            usernameEditText.setError("Le nom d'utilisateur est requis.");
            hasError = true;
        } else {
            // Effacer l'erreur si le champ n'est pas vide
            usernameEditText.setError(null);
        }

        // 4. Validation du Mot de passe
        if (password.isEmpty()) {
            passwordEditText.setError("Le mot de passe est requis.");
            hasError = true;
        } else {
            passwordEditText.setError(null);
        }

        // 5. Action finale
        if (!hasError) {
            // TOUT EST VALIDE : Procéder à la connexion (logique métier)
            Toast.makeText(this, "Connexion réussie ! (Logique à implémenter)", Toast.LENGTH_SHORT).show();
            // Ici, vous ajouteriez la logique réelle comme l'appel API ou la navigation

            // 1. Créer l'Intent pour naviguer de MainActivity vers l'activité 'accueil'
            Intent intent = new Intent(MainActivity.this, Accueil.class);

            //pass the username to the next activity(optional)
            intent.putExtra("username", username);

            // 2. Lancer l'activité
            startActivity(intent);

            // Optionnel: Terminer l'activité de connexion pour empêcher l'utilisateur de revenir en arrière avec le bouton 'Retour'
            finish();
        } else {
            // Afficher un message général si la validation échoue
            Toast.makeText(this, "Veuillez corriger les erreurs de saisie.", Toast.LENGTH_SHORT).show();
        }
    }
}