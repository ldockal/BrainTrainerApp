package com.lukas_tamz.braintrainer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lukas_tamz.braintrainer.holders.ListEntryHolder;
import com.lukas_tamz.braintrainer.utils.Utils;

import java.util.List;

/**
 * Created by ldockal on 11/17/2017.
 */

public class CustomListView extends ArrayAdapter<Game> {

    private Context context;
    private int layoutResourceId;
    private List<Game> gameList;

    public CustomListView(Context context, int layoutResourceId, List<Game> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.gameList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListEntryHolder holder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater != null ? inflater.inflate(layoutResourceId, parent, false) : null;
            holder = new ListEntryHolder(row);
            row.setTag(holder);
        } else {
            holder = (ListEntryHolder) row.getTag();
        }

        Game game = gameList.get(position);
        initGolderWithGameValues(holder, game);

        return row;
    }


    private void initGolderWithGameValues(ListEntryHolder holder, Game game) {
        holder.gameTitle.setText(game.getTitle());
        holder.gameDesc.setText(game.getDesc());
        int imgId = Utils.getImageIdFromDrawable(context, game.getImgName());
        if (imgId == 0) {
            Log.d("img", game.getImgName() + " not found");
        } else {
            Log.d("img", game.getImgName() + " imgId: " + imgId);
            holder.gameImg.setImageResource(imgId);
        }

    }
}
