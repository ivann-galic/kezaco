package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class QuizzActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizzactivity);


        // récupérer le fichier JSon
        //le parser
        // lance une question aléatoire
        // récupérer le choix de l'utilisateur
        // comparer le choix de l'utilisateur à la bonne réponse
        // renvoyer true / false selon le résultat de la comparaison
        // afficher bonne réponse si c'est true
        // afficher mauvaise réponse si c'est false et afficher la bonne réponse
        // le bouton valider devient question suivante
        //question suivante charge une autre question, différente de celle qu'on vient de faire

    }
}
