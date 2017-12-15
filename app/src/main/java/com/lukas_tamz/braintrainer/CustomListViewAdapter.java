package com.lukas_tamz.braintrainer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.lukas_tamz.braintrainer.holders.ListEntryHolder;
import com.lukas_tamz.braintrainer.models.GameInfo;
import com.lukas_tamz.braintrainer.models.GameStatus;
import com.lukas_tamz.braintrainer.utils.Utils;

import java.util.List;

/**
 * Created by ldockal on 11/17/2017.
 */

public class CustomListViewAdapter extends ArrayAdapter<GameInfo>{

    private Context context;
    private int layoutResourceId;
    private List<GameInfo> gameInfoList;
    private GameGridActivity gameGridActivity;
    private GameStatus gameStatus;

    public CustomListViewAdapter(Context context, int layoutResourceId, List<GameInfo> objects) {
        super(context, layoutResourceId, objects);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.gameInfoList = objects;
    }


    private void initGolderWithGameValues(ListEntryHolder holder, GameInfo gameInfo) {
        holder.gameTitle.setText(gameInfo.getTitle());
        holder.gameDesc.setText(gameInfo.getDesc());
        int imgId = Utils.getImageIdFromDrawable(context, gameInfo.getImgName());
        if (imgId == 0) {
            Log.d("img", gameInfo.getImgName() + " not found");
        } else {
            Log.d("img", gameInfo.getImgName() + " imgId: " + imgId);
            holder.gameImg.setImageResource(imgId);
        }

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ListEntryHolder holder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater != null ? inflater.inflate(layoutResourceId, parent, false) : null;
            holder = new ListEntryHolder(row);
            if (row != null) {
                row.setTag(holder);
            }
        } else {
            holder = (ListEntryHolder) row.getTag();
        }

        GameInfo gameInfo = gameInfoList.get(position);
        initGolderWithGameValues(holder, gameInfo);

        return row;
    }

    public GameGridActivity getGameGridActivity() {
        return gameGridActivity;
    }

    public void setGameGridActivity(GameGridActivity gameGridActivity) {
        this.gameGridActivity = gameGridActivity;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }
}
