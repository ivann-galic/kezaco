package com.ivann.kezaco;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity    {


        @SuppressLint("SetTextI18n")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_results);

        Intent srcIntent = getIntent();
        final ArrayList medias = srcIntent.getParcelableArrayListExtra("listOjectJson");
        int counter = srcIntent.getIntExtra("numQuestion", 0);
        int nbOfGoodAnswers = srcIntent.getIntExtra("nbOfGoodAnswers", 0);
        String difficultyChosen = srcIntent.getStringExtra("difficultyChosen");
        TextView finalRate = findViewById(R.id.TextViewPercentWin);

        double test = ((double)nbOfGoodAnswers / medias.size()) * 100;
        finalRate.setText((int)test + " %");

        TextView textViewDifficulty = findViewById(R.id.textViewDifficulty);
        textViewDifficulty.setText("Difficulté choisie " + difficultyChosen);

    }
}
