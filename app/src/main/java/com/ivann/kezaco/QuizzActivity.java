package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class QuizzActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizzactivity);
        Intent srcIntent = getIntent();
        ArrayList medias = srcIntent.getParcelableArrayListExtra("list");
        final ImageView imageViewAnimal = findViewById(R.id.imageViewAnimal);
        final Button buttonSound = findViewById(R.id.buttonSound);
        int index = 0;
        assert medias != null;
        Log.i("QuizzActivity", "recupére" + medias.get(index));
        final Media media = (Media) medias.get(index);
        final String image = media.media;
        final String theme = media.theme;
        ArrayList answer = media.answers;


        //medias.get(0);

        final RadioGroup group = (RadioGroup) findViewById(R.id.radioGroupQuest);
        RadioButton button;

        assert image != null;
        ImageView imageView = findViewById(R.id.imageViewAnimal);
        String identifier = image.substring(0, image.lastIndexOf("."));
        imageViewAnimal.setImageResource(getResources().getIdentifier(identifier, "drawable", getPackageName()));

        assert media.theme != null;
        if (media.theme.equals("picture") || media.theme.equals("shadow")) {
            findViewById(R.id.imageViewAnimal).setVisibility(View.VISIBLE);
            findViewById(R.id.buttonSound).setVisibility(View.INVISIBLE);
        } else if (media.theme.equals("sound")) {
            findViewById(R.id.buttonSound).setVisibility(View.VISIBLE);
            findViewById(R.id.imageViewAnimal).setVisibility(View.INVISIBLE);
        }

        buttonSound.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String RESOURCE_PATH = ContentResolver.SCHEME_ANDROID_RESOURCE + "://";
                String sSound = image.substring(0, image.lastIndexOf("."));
                int idSound = getResources().getIdentifier(sSound, "raw", getPackageName());
                String pathSound = RESOURCE_PATH + getPackageName() + File.separator + idSound;
                Uri uriSound = Uri.parse(pathSound);

                MediaPlayer mp = new MediaPlayer();
                mp = MediaPlayer.create(QuizzActivity.this, uriSound);
                mp.start();
            }
        });

        assert answer != null;
        for (Answer item : media.answers) {
            RadioButton rb = new RadioButton(QuizzActivity.this);
            rb.setText(item.sentence);
            group.addView(rb);
        }

        final Button buttonValidNext = (Button) findViewById(R.id.buttonValidNext);
        final TextView TextViewResultTurn = (TextView) findViewById(R.id.textViewResultTurn);
        final TextView textViewResultTurn;
        final String[] playerChoice = {""};

        String goodAnswer = "";
        buttonValidNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final RadioButton radio_red = (RadioButton) findViewById(group.getCheckedRadioButtonId());

                String goodAnswer = "";
                if (radio_red != null) {
                    playerChoice[0] = (String) radio_red.getText();
                }
                Log.i("radioRed", "radioRed: " + radio_red);
                for (Answer item : media.answers) {
                    if (item.isGoodAnswer == true) {
                        goodAnswer = item.sentence;
                    }
                }
                Log.i("goodAnswer", "onClick: " + goodAnswer);
                assert goodAnswer != null;
                if (goodAnswer.equals(playerChoice[0])) {

                    TextViewResultTurn.setText("Bonne réponse");
                } else {
                    TextViewResultTurn.setText("Mauvaise réponsee");
                }

                //String test = media.media;
                //   int testos = R.drawable.test;
                //   ImageView pictureView = findViewById(R.id.imageView3)
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
        });
    }
}