package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class QuizzActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizzactivity);
        Intent srcIntent = getIntent();
        ArrayList medias = srcIntent.getParcelableArrayListExtra("list");
        final ImageView imageViewAnimal = findViewById(R.id.imageViewAnimal);
        final RadioGroup radioGroup = findViewById(R.id.radioGroupQuest);
        int index = 0;
        assert medias != null;
        Log.i("QuizzActivity","recupére" + medias.get(index));
       Media media = (Media) medias.get(index);
       String image = media.media;

       String theme = media.theme;
       ArrayList answer = media.answers;


        //medias.get(0);

        RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupQuest);
        RadioButton button;

        assert image != null;
        ImageView imageView = findViewById(R.id.imageViewAnimal);
        String identifier = image.substring(0, image.lastIndexOf("."));
        imageViewAnimal.setImageResource(getResources().getIdentifier(identifier, "drawable", getPackageName()));

        assert media.theme != null;
        if (media.theme.equals("picture") || media.theme.equals("shadow")) {
            findViewById(R.id.imageViewAnimal).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonSound).setVisibility(View.INVISIBLE);
        } else if (media.theme.equals("sound")){
            findViewById(R.id.buttonSound).setVisibility(View.VISIBLE);
            findViewById(R.id.imageViewAnimal).setVisibility(View.INVISIBLE);
        }


       assert answer != null;
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
