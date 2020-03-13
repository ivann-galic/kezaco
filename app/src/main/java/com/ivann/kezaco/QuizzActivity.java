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
import java.util.ArrayList;
import java.util.Collections;

public class QuizzActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String TAG = "QuizzActivity";
    private int nbOfGoodAnswers;
    private int counter;
    private int numQuestion;

    private String difficultyChosen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_quizzactivity);
        Intent srcIntent = getIntent();
        difficultyChosen = srcIntent.getStringExtra("difficultyChosen");
        final ArrayList medias = srcIntent.getParcelableArrayListExtra("listOjectJson");
        counter = srcIntent.getIntExtra("counter", 0);
        nbOfGoodAnswers = srcIntent.getIntExtra("nbOfGoodAnswers", 0);
        numQuestion = counter;
        final int numberQuestions;
        numberQuestions = medias.size();

        final ImageView imageViewAnimal = findViewById(R.id.imageViewAnimal);
        final Button buttonSound = findViewById(R.id.buttonSound);
        final TextView textViewGoodSentence = findViewById(R.id.textViewGoodSentence);

        int index = counter;
        assert medias != null;

        final Media media = (Media) medias.get(index);
        final String image = media.media;
        final String theme = media.theme;
        ArrayList answer = media.answers;
        Collections.shuffle(answer);

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
        TextView tvPaginationQuestion = findViewById(R.id.TextViewDifficultyQuestion);
        tvPaginationQuestion.setText("QUESTION " + (counter+1) + "/ " + medias.size());

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
        final Button buttonNextQuestion = findViewById(R.id.buttonNextQuestion);

        final TextView TextViewResultTurn = (TextView) findViewById(R.id.textViewResultTurn);
        final TextView textViewResultTurn;
        final String[] playerChoice = {""};

        String goodAnswer = "";
        buttonValidNext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (buttonValidNext.getText().equals("Valider la réponse")) {
                    final RadioButton radio_red = (RadioButton) findViewById(group.getCheckedRadioButtonId());
                    findViewById(R.id.radioGroupQuest).setVisibility(View.INVISIBLE);
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
                        textViewGoodSentence.setText("Bien joué !");

                        nbOfGoodAnswers = nbOfGoodAnswers + 1;
                    } else {
                        TextViewResultTurn.setText("Mauvaise réponse");
                        textViewGoodSentence.setText("La bonne réponse était : " + goodAnswer);

                    }
                    buttonNextQuestion.setVisibility(View.VISIBLE);
                    buttonValidNext.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonNextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (counter < numberQuestions - 1) {
                    counter += 1;
                    final Intent intent = new Intent(QuizzActivity.this, QuizzActivity.class);
                    intent.putExtra("counter", counter);
                    intent.putExtra("nbOfGoodAnswers", nbOfGoodAnswers);
                    intent.putExtra("numberQuestions", medias.size());
                    intent.putParcelableArrayListExtra("listOjectJson", medias);
                    intent.putExtra("difficultyChosen", difficultyChosen);
                    Log.i("tag", "onClick: 11111111111111.2" + difficultyChosen);
                    startActivity(intent);
                    finish();
                } else if (counter == numberQuestions - 1) {
                    final Intent intent = new Intent(QuizzActivity.this, ResultsActivity.class);
                    intent.putExtra("counter", counter);
                    intent.putExtra("numberQuestions", medias.size());
                    intent.putExtra("nbOfGoodAnswers", nbOfGoodAnswers);
                    intent.putParcelableArrayListExtra("listOjectJson", medias);
                    intent.putExtra("difficultyChosen", difficultyChosen);
                    Log.i("tag", "onClick: 11111111111111" + difficultyChosen);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        Log.d(TAG, " Name " + ((RadioButton) v).getText() + " Id is " + v.getId());
    }
}




