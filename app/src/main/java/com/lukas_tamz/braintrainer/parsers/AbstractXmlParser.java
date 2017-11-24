package com.lukas_tamz.braintrainer.parsers;

import android.util.Xml;

import com.lukas_tamz.braintrainer.models.GameInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ldockal on 11/17/2017.
 */

public abstract class AbstractXmlParser<T extends GameInfo> {

    protected XmlPullParser parser;
    protected List<T> objects;


    public AbstractXmlParser(InputStream inputStream) throws XmlPullParserException, IOException {
        this.parser = Xml.newPullParser();
        this.parser.setInput(inputStream, null);
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.nextTag();
    }

    public AbstractXmlParser(XmlPullParser resourceParser) throws XmlPullParserException, IOException {
        this.parser = resourceParser;
    }

    abstract void handleElementStart(String elementName) throws IOException, XmlPullParserException;

    abstract void handleElementEnd(String elementName);

    protected void handleStartDocument() {
        objects = new ArrayList<>();
    }


    public List<T> loadObjects() throws XmlPullParserException, IOException {
        int eventType = parser.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                case XmlPullParser.START_DOCUMENT:
                    handleStartDocument();
                    break;
                case XmlPullParser.START_TAG:
                    handleElementStart(parser.getName());

                    break;
                case XmlPullParser.END_TAG:
                    handleElementEnd(parser.getName());
            }
            eventType = parser.next();
        }
        return objects;
    }

    protected String readText(XmlPullParser parser) throws IOException, XmlPullParserException {
        String result = "";
        if (parser.next() == XmlPullParser.TEXT) {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }
}
