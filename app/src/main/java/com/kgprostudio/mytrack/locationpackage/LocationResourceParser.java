package com.kgprostudio.mytrack.locationpackage;

import org.xmlpull.v1.XmlPullParser;

import java.util.ArrayList;

public class LocationResourceParser {

    public ArrayList<LocationClass> getLocations() {
        return locations;
    }
    private ArrayList<LocationClass> locations;

    public LocationResourceParser() {
        locations = new ArrayList<>();
    }

    public boolean parse(XmlPullParser xpp) {
        boolean status = true;
        id_class id_cl = null;
        boolean inEntry = false;
        String textValue = "";

        try {
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {

                String tagName = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if ("id".equalsIgnoreCase(tagName)) {
                            inEntry = true;
                            id_cl = new id_class();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        textValue = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (inEntry) {
                            if ("id".equalsIgnoreCase(tagName)) {

                                inEntry = false;
                            } else if ("name".equalsIgnoreCase(tagName)) {

                            } else if ("age".equalsIgnoreCase(tagName)) {

                            }
                        }
                        break;
                    default:
                }
                eventType = xpp.next();
            }
        } catch (Exception e) {
            status = false;
            e.printStackTrace();
        }
        return status;
    }
}

