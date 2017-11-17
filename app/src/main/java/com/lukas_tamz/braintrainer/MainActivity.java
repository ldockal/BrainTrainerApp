package com.lukas_tamz.braintrainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends Activity {
    ListView listView;
    List<Game> gameList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.options_menu, menu);
        loadComponentsInView();
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

    private void loadComponentsInView() {
        this.listView = findViewById(R.id.gameList);
        dummyInit();
        CustomListView customListView = new CustomListView(this, R.layout.minigames_list, gameList);
        this.listView.setAdapter(customListView);
    }

    private void dummyInit() {
        gameList.add(new Game("title 1", "Desc 1", "img 1"));
        gameList.add(new Game("title 2", "Desc 2", "img 2"));
        gameList.add(new Game("title 3", "Desc 3", "img 3"));
        gameList.add(new Game("title 4", "Desc 4", "img 4"));
        gameList.add(new Game("title 5", "Desc 5", "img 5"));
    }
}
