package com.ivann.kezaco;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class quizzList extends AppCompatActivity {

    MediaAdapter adapter;
    ArrayList<Media> medias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent srcIntent = getIntent();
         medias = srcIntent.getParcelableArrayListExtra("medias");

        setContentView(R.layout.activity_quizz_list);
        adapter = new MediaAdapter(medias);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}
