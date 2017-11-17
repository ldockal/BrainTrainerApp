package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.lukas_tamz.braintrainer.exceptions.GamesNotFoundException;
import com.lukas_tamz.braintrainer.parsers.AbstractXmlParser;
import com.lukas_tamz.braintrainer.parsers.GameListParser;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    ListView listView;
    List<Game> gameList;


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
            Toast.makeText(this, "about", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.options_menu_settings) {
            Toast.makeText(this, "settings", Toast.LENGTH_SHORT).show();
        }
        if (item.getItemId() == R.id.options_menu_exit) {
            Toast.makeText(this, "exit", Toast.LENGTH_SHORT).show();
        }

        return super.onOptionsItemSelected(item);
    }

    private void loadComponentsInView() throws GamesNotFoundException {
        this.listView = findViewById(R.id.gameList);
        //dummyInit();

        try {
           XmlResourceParser xmlResourceParser = getResources().getXml(R.xml.games_list);
            AbstractXmlParser<Game> abstractXmlParser = new GameListParser(xmlResourceParser);
            gameList = abstractXmlParser.loadObjects();
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
        if (gameList != null && !gameList.isEmpty()) {
            CustomListView customListView = new CustomListView(this, R.layout.minigames_list_item, gameList);
            this.listView.setAdapter(customListView);
        }else {
            throw new GamesNotFoundException("Games list is empty");
        }

    }

    private void dummyInit() {
        gameList = new ArrayList<>();
        gameList.add(new Game("1","title 1", "Desc 1", "img 1"));
        gameList.add(new Game("2","title 2", "Desc 2", "img 2"));
    }
}
