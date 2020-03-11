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
    }
}
