package com.lukas_tamz.braintrainer.holders;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lukas_tamz.braintrainer.R;

/**
 * Created by ldockal on 11/17/2017.
 */

public class ListEntryHolder {
    public final ImageView gameImg;
    public final TextView gameTitle;
    public final TextView gameDesc;

    public ListEntryHolder(View view) {
        gameDesc = view.findViewById(R.id.gameDesc);
        gameTitle = view.findViewById(R.id.gameTitle);
        gameImg = view.findViewById(R.id.gameImg);
    }
}
