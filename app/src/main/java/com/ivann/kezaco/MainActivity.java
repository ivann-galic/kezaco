package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.buttonAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAbout();
            }
        });

        findViewById(R.id.buttonChooseQuizz).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToQuizz();
            }
        });
    }

    private void goToAbout() {
        final Intent intentAbout = new Intent(this, AboutActivity.class);
        Button buttonAbout =  findViewById(R.id.buttonAbout);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentAbout);
            }
        });
    }

    private void goToQuizz() {
        final Intent intentQuizz = new Intent(this, QuizzActivity.class);
        Button buttonAbout =  findViewById(R.id.buttonChooseQuizz);
        buttonAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intentQuizz);
            }
        });
    }
}
