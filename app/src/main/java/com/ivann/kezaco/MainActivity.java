package com.ivann.kezaco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    String[] listItems;
    MediaAdapter adapter;
    List<Media> medias;
    private JSONArray jsonArrayAdapt = new JSONArray();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       medias = new ArrayList<>();
        for (int i = 0; i < jsonArrayAdapt.length(); i++) {

        }



        Button buttonList = (Button) findViewById(R.id.buttonRecycle);
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getQuizzFromApi(-1,"slip de bain",quizzList.class);


            }
        });

        listItems = getResources().getStringArray(R.array.shopping_item);
        Button choiceButton = (Button) findViewById(R.id.buttonChooseQuizz);
        choiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
            mBuilder.setTitle("Quel est l'âge de votre enfant?");
            mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    ListView lw = ((AlertDialog) dialogInterface).getListView();
                    int checkedItemPosition = lw.getCheckedItemPosition();
                    String difficultyChosen = listItems[checkedItemPosition];
                    getQuizzFromApi(checkedItemPosition, difficultyChosen, QuizzActivity.class);
                    dialogInterface.dismiss();
                }
            });
            AlertDialog mDialog = mBuilder.create();
            mDialog.show();
            }
        });
        findViewById(R.id.buttonAbout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAbout();

            }
        });
    }

    private void getQuizzFromApi(int userChoice, final String difficultyChosen, final Class destinationActivity) {
        String url = urlFromUserChoice(userChoice);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                Log.e("MainActivity", "onFailure: ", e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                String body = response.body().string();
                try {
                    JSONArray jsonArray = new JSONArray(body);
                    ArrayList<Media> list = new ArrayList<>();
                    int len = jsonArray.length();

                    for (int i = 0; i < len; i++) {
                        ArrayList<Answer> answerList = new ArrayList<>();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String mediaAttribute = jsonObject.getString("media");
                        String theme = jsonObject.getString("theme");
                        JSONArray answer = jsonObject.getJSONArray("answers");
                            for (int j = 0; j < answer.length(); j++) {
                                Log.i("MainActivity", "longueur liste ?" + answer.length());
                                JSONObject answerJSONObject = answer.getJSONObject(j);
                                Answer answer1 = new Answer(answerJSONObject.getString("sentence"), answerJSONObject.getBoolean("is_right"));
                                Log.i("MainActivity", "affiche liste ?" + answer1);
                                answerList.add(answer1);
                            }
                            list.add(new Media(mediaAttribute, theme, answerList));
                        medias.add(new Media(mediaAttribute, theme, answerList));

                    }

                    Collections.shuffle(list);
                    Intent intent = new Intent(MainActivity.this, destinationActivity);
                    intent.putExtra("difficultyChosen", difficultyChosen);
                    intent.putExtra("listOjectJson", list);
                    //intent.putExtra("medias",  medias);
                    startActivity(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                    e.getMessage();
                }
            }
        });

        findViewById(R.id.buttonAbout).setOnClickListener(this);
    }

    /**
     * @param randomTab
     * @return
     */
    private int verifyRandom(ArrayList randomTab) {
        int random;
        if (randomTab.isEmpty()) {
            random = (int) Math.floor(Math.random() * 3);
            randomTab.add(random);
        }
        do {
            random = (int) Math.floor(Math.random() * 3);

        } while (randomTab.contains(random));
        return random;
    }

    private String urlFromUserChoice(int userChoice) {
        String theme;
        switch (userChoice) {
            case -1:
                theme = "";
            case 0:
                theme = "sound";
                break;
            case 1:
                theme = "picture";
                break;
            case 2:
                theme = "shadow";
                break;
            default:
                theme = "sound";
                break;
        }

        String queryParam = "";
        if (!theme.isEmpty()) {
            queryParam = "?theme=" + theme;
        }
        return "http://gryt.tech:8080/kezacos/" + queryParam;
    }

    private void goToAbout() {
        final Intent intentAbout = new Intent(this, AboutActivity.class);
        startActivity(intentAbout);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonAbout:
                goToAbout();
                break;
        }
    }
}
