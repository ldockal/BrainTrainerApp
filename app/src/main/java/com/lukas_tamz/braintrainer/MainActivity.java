package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.lukas_tamz.braintrainer.exceptions.GamesNotFoundException;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.parsers.AbstractXmlParser;
import com.lukas_tamz.braintrainer.parsers.GameListParser;
import com.lukas_tamz.braintrainer.utils.Utils;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
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
        }
        if (item.getItemId() == R.id.options_menu_exit) {
            Utils.displayintToast(this, "exit");
        }

        return super.onOptionsItemSelected(item);
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
            listView.setOnItemClickListener(new ListView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    GameInfo gameInfo = customListViewAdapter.getItem(position);
                    Intent intent = new Intent(getBaseContext(), GameGridActivity.class);
                    intent.putExtra(GameInfo.NAME, gameInfo);
                    startActivity(intent);
                }
            });
        } else {
            throw new GamesNotFoundException("Games list is empty");
        }

    }

    private void dummyInit() {
        gameInfoList = new ArrayList<>();
        gameInfoList.add(new GameInfo("1", "title 1", "Desc 1", "img 1"));
        gameInfoList.add(new GameInfo("2", "title 2", "Desc 2", "img 2"));
    }
}
