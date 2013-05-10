package be.itsworking.dpl.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Xml;
import be.itsworking.dpl.to.MyPharmacy;

public class PharmacyXMLParser
{

	private static final String ns = null;

	/**
	 * This is the only function need to be called from outside the class
	 * 
	 * @param reader
	 * @return ArrayList<MyPharmacy>
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	public ArrayList<MyPharmacy> parse() throws XmlPullParserException, IOException
	{
		try
		{
			InputStream reader = getClass().getResourceAsStream("db.xml");
			XmlPullParser parser = Xml.newPullParser();
			parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
			parser.setInput(reader, "utf-8");
			parser.nextTag();
			return readPharmacies(parser);
		}
		finally
		{
		}
	}

	/**
	 * This method read each pharmacy in the xml data and add it to ArrayList
	 * 
	 * @param parser
	 * @return ArrayList<MyPharmacy>
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private ArrayList<MyPharmacy> readPharmacies(XmlPullParser parser) throws XmlPullParserException, IOException
	{

		ArrayList<MyPharmacy> list = new ArrayList<MyPharmacy>();

		parser.require(XmlPullParser.START_TAG, ns, "duty");

		while (parser.next() != XmlPullParser.END_TAG)
		{
			if (parser.getEventType() != XmlPullParser.START_TAG)
			{
				continue;
			}

			String name = parser.getName();
			if (name.equals("pharmacy"))
			{
				list.add(readPharmacy(parser));
			}
			else
			{
				skip(parser);
			}
		}
		return list;
	}

	/**
	 * This method read a pharmacy and returns its corresponding MyPharmacy
	 * object
	 * 
	 * @param parser
	 * @return
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private MyPharmacy readPharmacy(XmlPullParser parser) throws XmlPullParserException, IOException
	{

		parser.require(XmlPullParser.START_TAG, ns, "pharmacy");
		// code="536301" lat="50.4732896" lng="4.0087885"
		// distance="18.8331768846"
		int id = Integer.parseInt(parser.getAttributeValue(ns, "code"));
		double lat = Double.parseDouble(parser.getAttributeValue(ns, "lat"));
		double lng = Double.parseDouble(parser.getAttributeValue(ns, "lng"));

		String nom = "";
		String address = "";
		String tel = "";

		while (parser.next() != XmlPullParser.END_TAG)
		{
			if (parser.getEventType() != XmlPullParser.START_TAG)
			{
				continue;
			}

			String name = parser.getName();

			if (name.equals("name"))
			{
				nom = readName(parser);
			}
			else if (name.equals("address"))
			{
				address = readAddress(parser);
			}
			else if (name.equals("phone"))
			{
				tel = readPhone(parser);
			}
			else
			{
				skip(parser);
			}
		}

		return new MyPharmacy(id, nom, address, tel, lat, lng);
	}

	/**
	 * Process name tag in the xml data
	 * 
	 * @param parser
	 * @return String
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private String readName(XmlPullParser parser) throws IOException, XmlPullParserException
	{
		parser.require(XmlPullParser.START_TAG, ns, "name");
		return readText(parser);
	}

	/**
	 * Process address tag in the xml data
	 * 
	 * @param parser
	 * @return String
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private String readAddress(XmlPullParser parser) throws IOException, XmlPullParserException
	{
		parser.require(XmlPullParser.START_TAG, ns, "address");
		return readText(parser);
	}

	/**
	 * Process phone tag in the xml data
	 * 
	 * @param parser
	 * @return String
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private String readPhone(XmlPullParser parser) throws IOException, XmlPullParserException
	{
		parser.require(XmlPullParser.START_TAG, ns, "phone");
		return readText(parser);
	}

	/**
	 * Getting Text from an element
	 * 
	 * @param parser
	 * @return
	 * @throws IOException
	 * @throws XmlPullParserException
	 */
	private String readText(XmlPullParser parser) throws IOException, XmlPullParserException
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
	 * @param parser
	 * @throws XmlPullParserException
	 * @throws IOException
	 */
	private void skip(XmlPullParser parser) throws XmlPullParserException, IOException
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