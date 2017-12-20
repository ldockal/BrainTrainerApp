package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lukas_tamz.braintrainer.exceptions.GamesNotFoundException;
import com.lukas_tamz.braintrainer.holders.ConstantsHolder;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.parsers.AbstractXmlParser;
import com.lukas_tamz.braintrainer.parsers.GameListParser;
import com.lukas_tamz.braintrainer.utils.SharedPreferenceHelper;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MiniGamesActivity extends Activity {
    ListView listView;
    List<GameInfo> gameInfoList;
    private SharedPreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_games);
        preferenceHelper = new SharedPreferenceHelper(getApplicationContext(), ConstantsHolder.GAMES_PREF_NAMES, Context.MODE_PRIVATE);
        try {
            loadComponentsInView();
        } catch (GamesNotFoundException e) {
            Log.e("onCreate", e.getMessage());
        }
    }

    private void saveGameNamesToPref(List<GameInfo> games) {

        StringBuilder builder = new StringBuilder();

        for (GameInfo gameInfo : games) {
            builder.append("id")
                    .append(gameInfo.getId())
                    .append("|")
                    .append(gameInfo.getTitle())
                    .append("|")
                    .append("::");
        }

        preferenceHelper.setSharedPreferenceString(ConstantsHolder.GAMES_PREF_NAMES_KEY, builder.toString());
    }

    private void loadComponentsInView() throws GamesNotFoundException {
        this.listView = findViewById(R.id.gameList);

        try {
            XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.games_list);
            AbstractXmlParser<GameInfo> abstractXmlParser = new GameListParser(xmlResourceParser);
            gameInfoList = abstractXmlParser.loadObjects();
            saveGameNamesToPref(gameInfoList);
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        if (gameInfoList != null && !gameInfoList.isEmpty()) {
            final CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(this, R.layout.minigames_list_item, gameInfoList);
            this.listView.setAdapter(customListViewAdapter);
            listView.setOnItemClickListener(new ListView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    GameInfo gameInfo = customListViewAdapter.getItem(position);
                    final Intent intent = new Intent();

                    if (gameInfo == null) {
                        throw new NullPointerException("game info is null.");
                    }
                    if (gameInfo.getType().equals("grid")) {
                        intent.setClass(getApplicationContext(), GameGridActivity.class);
                        //intent = new Intent(getBaseContext(), GameGridActivity.class);
                        intent.putExtra(GameInfo.NAME, gameInfo);

                    } else if (gameInfo.getType().equals("math")) {
                        //intent = new Intent(getBaseContext(), MathGameActivity.class);
                        intent.setClass(getBaseContext(), MathGameActivity.class);
                        intent.putExtra(GameInfo.NAME, gameInfo);
                    }

                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

                    alertDialogBuilder.setTitle(gameInfo.getTitle());
                    final AlertDialog dialog = alertDialogBuilder
                            .setMessage(gameInfo.getInstruction())
                            .setCancelable(false)
                            .setPositiveButton("Rozumim", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    dialog.dismiss();
                                    startActivity(intent);
                                }
                            }).create();

                    dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
                    dialog.show();
                }
            });
        } else {
            throw new GamesNotFoundException("Games list is empty");
        }

    }

    public void gotoMenu(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
