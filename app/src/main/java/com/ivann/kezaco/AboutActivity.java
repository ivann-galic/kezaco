package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView tv = findViewById(R.id.versionTextView);
        tv.setText(BuildConfig.VERSION_NAME);

        TextView TextViewAboutSpeech = findViewById(R.id.TextViewAboutSpeech);
        TextViewAboutSpeech.setText("Kezaco est une application créée le 11 mars 2020  par 4 étudiants de la Coding Factory. L'objectif est simple : permettre aux enfants de reconnaître les animaux par différents moyens ludiques");

        TextView textViewCreators = findViewById(R.id.textViewCreators);
        textViewCreators.setText(" Créateurs : Ivann.G, Romain.D, Hugo.P, Jérémy.G");
    }
}
