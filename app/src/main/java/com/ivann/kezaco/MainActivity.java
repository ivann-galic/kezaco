package com.ivann.kezaco;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String[] listItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listItems  = getResources().getStringArray(R.array.shopping_item);

        getQuizzFromApi();

        Button choiceButton = (Button) findViewById(R.id.buttonChooseQuizz);
//        final TextView mResult = (TextView) findViewById(R.id.tvResult);
        choiceButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
                                                mBuilder.setTitle("Quel est l'âge de votre enfant?");
                                                mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialogInterface, int i) {
//                        mResult.setText(listItems[i]);
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
                /*
                findViewById(R.id.buttonChooseQuizz).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToQuizz();
                    }
                });*/
            }

    private void getQuizzFromApi() {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://gryt.tech:8080/kezacos/?theme=sound")
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
                    for(int i = 0;i < jsonArray.length();i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
String media = jsonObject.getString("media");
                        Log.i("MainActivity", "onResponse: " + media);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });
    }



    private void goToAbout() {
                final Intent intentAbout = new Intent(this, AboutActivity.class);
                Button buttonAbout = findViewById(R.id.buttonAbout);
                buttonAbout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intentAbout);
                    }
                });
            }

            private void goToQuizz() {
                final Intent intentQuizz = new Intent(this, QuizzActivity.class);
                Button buttonAbout = findViewById(R.id.buttonChooseQuizz);
                buttonAbout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intentQuizz);

                    }
                });
            }
        }
