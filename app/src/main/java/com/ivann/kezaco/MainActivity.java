package com.ivann.kezaco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems = getResources().getStringArray(R.array.shopping_item);

        Button choiceButton = (Button) findViewById(R.id.buttonChooseQuizz);
//      final TextView mResult = (TextView) findViewById(R.id.tvResult);
        choiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                mBuilder.setTitle("Quel est l'âge de votre enfant?");
                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
//      mResult.setText(listItems[i]);
                        ListView lw = ((AlertDialog) dialogInterface).getListView();
                        int checkedItemPosition = lw.getCheckedItemPosition();
                        Log.i("MainActivity", "onClick: " + checkedItemPosition);
                        getQuizzFromApi(checkedItemPosition);
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

    private void getQuizzFromApi(int userChoice) {


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
                //Log.i("MainActivity","response=" + body);
                try {

                    JSONArray jsonArray = new JSONArray(body);

                    // Collections.shuffle(jsonArray);
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

                    }
                    Collections.shuffle(list);
                    Intent intent = new Intent(MainActivity.this, QuizzActivity.class);
                    intent.putExtra("list", list);
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
        return "http://gryt.tech:8080/kezacos/?theme=" + theme;
    }

    private void goToAbout() {
        final Intent intentAbout = new Intent(this, AboutActivity.class);
        startActivity(intentAbout);
    }

    private void goToQuizz() {
        final Intent intentQuizz = new Intent(this, QuizzActivity.class);
        startActivity(intentQuizz);

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
