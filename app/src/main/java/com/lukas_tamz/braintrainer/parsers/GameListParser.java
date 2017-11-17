package com.lukas_tamz.braintrainer.parsers;

import android.content.res.XmlResourceParser;

import com.lukas_tamz.braintrainer.Game;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ldockal on 11/17/2017.
 */

public final class GameListParser extends AbstractXmlParser<Game> {


    private static final String MAIN_TAG = "game";
    private static final String ID_TAG = "id";
    private static final String TITLE_TAG = "title";
    private static final String DESC_TAG = "desc";
    private static final String IMAGE_TAG = "image";

    private Game game = new Game();

    public GameListParser(InputStream inputStream) throws XmlPullParserException, IOException {
        super(inputStream);
    }

    public GameListParser(XmlResourceParser resourceParser) throws XmlPullParserException, IOException {
        super(resourceParser);
    }

    @Override
    void handleElementStart(String elementName) throws IOException, XmlPullParserException {
        switch (elementName) {
            case TITLE_TAG:
                game.setTitle(readText(parser));
                break;
            case ID_TAG:
                game.setId(readText(parser));
                break;
            case DESC_TAG:
                game.setDesc(readText(parser));
                break;
            case IMAGE_TAG:
                game.setImgName(readText(parser));
                break;

        }
    }

    @Override
    protected void handleStartDocument() {
        super.handleStartDocument();
        game = new Game();
    }

    @Override
    void handleElementEnd(String elementName) {

        if (elementName.equals(MAIN_TAG)) {
            objects.add(game);
            game = new Game();
        }
    }
}
