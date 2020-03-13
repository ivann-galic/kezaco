package com.ivann.kezaco;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class PageResults extends AppCompatActivity    {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_results);

            Intent srcIntent = getIntent();
            final ArrayList medias = srcIntent.getParcelableArrayListExtra("listOjectJson");
            int counter = srcIntent.getIntExtra("numQuestion", 0);
            int nbOfGoodAnswers = srcIntent.getIntExtra("nbOfGoodAnswers", 0);

        TextView finalRate = findViewById(R.id.tvPercentWin);
        Double test = ((double)nbOfGoodAnswers / medias.size()) * 100;
        finalRate.setText(String.valueOf(test));

    }
}
