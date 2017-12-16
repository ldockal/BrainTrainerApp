package com.lukas_tamz.braintrainer.parsers;

import android.content.res.XmlResourceParser;

import com.lukas_tamz.braintrainer.models.GameInfo;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by ldockal on 11/17/2017.
 */

public final class GameListParser extends AbstractXmlParser<GameInfo> {


    private static final String MAIN_TAG = "gameInfo";
    private static final String ID_TAG = "id";
    private static final String TITLE_TAG = "title";
    private static final String DESC_TAG = "desc";
    private static final String IMAGE_TAG = "image";
    private static final String MAX_REPEATS = "remainingRepeats";
    private static final String TYPE = "type";
    public static final String INSTRUCTION = "instruction";

    private GameInfo gameInfo = new GameInfo();

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
                gameInfo.setTitle(readText(parser));
                break;
            case ID_TAG:
                gameInfo.setId(readText(parser));
                break;
            case DESC_TAG:
                gameInfo.setDesc(readText(parser));
                break;
            case IMAGE_TAG:
                gameInfo.setImgName(readText(parser));
                break;
            case MAX_REPEATS:
                gameInfo.setMaxRepeats(readInt(parser));
                break;
            case TYPE:
                gameInfo.setType(readText(parser));
                break;
            case INSTRUCTION:
                gameInfo.setInstruction(readText(parser));
                break;
        }
    }

    @Override
    protected void handleStartDocument() {
        super.handleStartDocument();
        gameInfo = new GameInfo();
    }

    @Override
    void handleElementEnd(String elementName) {

        if (elementName.equals(MAIN_TAG)) {
            objects.add(gameInfo);
            gameInfo = new GameInfo();
        }
    }
}
