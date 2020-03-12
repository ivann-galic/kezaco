package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class QuizzActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizzactivity);
        Intent srcIntent = getIntent();
        Media media = srcIntent.getParcelableExtra("media");
        final ImageView imageViewAnimal = findViewById(R.id.imageViewAnimal);
        final RadioGroup radioGroup = findViewById(R.id.radioGroupQuest);
        Log.i("QuizzActivity","recupére" + media.theme);

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupQuest);
        RadioButton button;

        assert media.answers != null;
        for(Answer item : media.answers) {
            RadioButton rb = new RadioButton(QuizzActivity.this);
            rb.setText( item.sentence);
            radioGroup.addView(rb);
        }


        //String test = media.media;
      //   int testos = R.drawable.test;
     //   ImageView pictureView = findViewById(R.id.imageView3);
      //  pictureView.setImageResource(flag);


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
