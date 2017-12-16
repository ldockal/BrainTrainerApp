package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lukas_tamz.braintrainer.exceptions.GamesNotFoundException;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.parsers.AbstractXmlParser;
import com.lukas_tamz.braintrainer.parsers.GameListParser;
import com.lukas_tamz.braintrainer.utils.Utils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

public class MainActivity extends Activity {
    public static final int REQUEST_CODE_SETTINGS = 1;
    ListView listView;
    List<GameInfo> gameInfoList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            loadComponentsInView();
        } catch (GamesNotFoundException e) {
            Log.e("mainActivity-onCreate", "games not found", e);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.options_menu_about) {
            Utils.displayintToast(this, "about");
        }
        if (item.getItemId() == R.id.options_menu_settings) {
            Utils.displayintToast(this, "settings");
            Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
            startActivityForResult(intent, REQUEST_CODE_SETTINGS);
        }
        if (item.getItemId() == R.id.options_menu_exit) {
            Utils.displayintToast(this, "exit");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_SETTINGS) {
            // todo apply changed settings to app
        }
    }

    private void loadComponentsInView() throws GamesNotFoundException {
        this.listView = findViewById(R.id.gameList);
        //dummyInit();

        try {
            XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.games_list);
            AbstractXmlParser<GameInfo> abstractXmlParser = new GameListParser(xmlResourceParser);
            gameInfoList = abstractXmlParser.loadObjects();
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
}
