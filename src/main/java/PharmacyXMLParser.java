package be.itsworking.dpl;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class PharmacyXMLParser
{

    private static final String ns = null;

    public static ArrayList<MyPharmacy> parse(String xml) throws XmlPullParserException, IOException
    {

        StringReader reader = new StringReader(xml);
        XmlPullParser parser = Xml.newPullParser();
        parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        parser.setInput(reader);
        parser.nextTag();
        return readPharmacies(parser);
    }

    /**
     * This method read each pharmacy in the xml data and add it to ArrayList
     *
     * @param parser XmlPullParser
     * @return ArrayList<MyPharmacy>
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static ArrayList<MyPharmacy> readPharmacies(XmlPullParser parser) throws XmlPullParserException, IOException
    {

        ArrayList<MyPharmacy> list = new ArrayList<MyPharmacy>();

        parser.require(XmlPullParser.START_TAG, ns, "duty");

        while (parser.next() != XmlPullParser.END_TAG)
        {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String name = parser.getName();
            if (name.equals("pharmacy"))
                list.add(readPharmacy(parser));
            else
                skip(parser);
        }
        return list;
    }

    /**
     * This method read a pharmacy and returns its corresponding MyPharmacy
     * object
     *
     * @param parser XmlPullParser
     * @return pharmacy MyPharmacy
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static MyPharmacy readPharmacy(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        parser.require(XmlPullParser.START_TAG, ns, "pharmacy");

        int id = Integer.parseInt(parser.getAttributeValue(ns, "code"));
        double lat = Double.parseDouble(parser.getAttributeValue(ns, "lat"));
        double lng = Double.parseDouble(parser.getAttributeValue(ns, "lng"));
        double dist = Double.parseDouble(parser.getAttributeValue(ns, "distance"));
        String nom = "";
        String pharmacist = "";
        String address = "";
        String cp = "";
        String city = "";
        String tel = "";
        String url = "";
        String hours = "";

        while (parser.next() != XmlPullParser.END_TAG)
        {
            if (parser.getEventType() != XmlPullParser.START_TAG)
                continue;

            String tagName = parser.getName();

            if (tagName.equals("name"))
                nom = readTag(parser, tagName);
            else if (tagName.equals("pharmacist"))
                pharmacist = readTag(parser, tagName);
            else if (tagName.equals("address"))
                address = readTag(parser, tagName);
            else if (tagName.equals("postalcode"))
                cp = readTag(parser, tagName);
            else if (tagName.equals("city"))
                city = readTag(parser, tagName);
            else if (tagName.equals("phone"))
                tel = readTag(parser, tagName);
            else if (tagName.equals("url"))
                url = readTag(parser, tagName);
            else if (tagName.equals("hours"))
                hours = readTag(parser, tagName);
            else
                skip(parser);
        }
        return new MyPharmacy(id, nom, pharmacist, address, cp, city, tel, url, lat, lng, dist, hours);
    }

    private static String readTag(XmlPullParser parser, String tag) throws IOException, XmlPullParserException
    {
        parser.require(XmlPullParser.START_TAG, ns, tag);
        return readText(parser);
    }

    /**
     * Getting Text from an element
     *
     * @param parser XmlPullParser
     * @return String
     * @throws IOException
     * @throws XmlPullParserException
     */
    private static String readText(XmlPullParser parser) throws IOException, XmlPullParserException
    {
        String result = "not-text";
        if (parser.next() == XmlPullParser.TEXT)
        {
            result = parser.getText();
            parser.nextTag();
        }
        return result;
    }

    /**
     * Move the cursor to the next tag
     *
     * @param parser XmlPullParser
     * @throws XmlPullParserException
     * @throws IOException
     */
    private static void skip(XmlPullParser parser) throws XmlPullParserException, IOException
    {
        if (parser.getEventType() != XmlPullParser.START_TAG)
        {
            throw new IllegalStateException();
        }
        int depth = 1;
        while (depth != 0)
        {
            switch (parser.next())
            {
                case XmlPullParser.END_TAG:
                    depth--;
                    break;
                case XmlPullParser.START_TAG:
                    depth++;
                    break;
            }
        }
    }
}