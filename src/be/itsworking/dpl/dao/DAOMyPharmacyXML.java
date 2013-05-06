/**
 * 
 */
package be.itsworking.dpl.dao;

import static be.itsworking.dpl.tools.Util.log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;

import android.content.ContentValues;
import android.content.Context;
import android.widget.Toast;
import be.itsworking.dpl.dao.DAOMyPharmacy;
import be.itsworking.dpl.to.MyPharmacy;
import be.itsworking.dpl.tools.Util;

/**
 * @author hackrylix
 * 
 */
public class DAOMyPharmacyXML implements DAOMyPharmacy
{

	private final ArrayList<MyPharmacy> pharmacyList;
	private Context context;

	public DAOMyPharmacyXML(Context context)
	{
		this.pharmacyList = new ArrayList<MyPharmacy>();
		this.context = context;
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try
		{
			builder = builderFactory.newDocumentBuilder();
			Document document = builder.parse(getClass().getResourceAsStream("db.xml"));
			Element rootElement = document.getDocumentElement();
			NodeList nodes = rootElement.getChildNodes();

			for (int i = 0; i < nodes.getLength(); i++)
			{
				Node node = nodes.item(i);

				if (node instanceof Element)
				{
					// a child element to process
					Element child = (Element) node;
					int id = Integer.parseInt(child.getAttribute("id"));
					String name = child.getAttribute("name");
					String adress = child.getAttribute("adress");
					String tel = child.getAttribute("tel");
					double lat = Double.valueOf(child.getAttribute("lat"));
					double lng = Double.valueOf(child.getAttribute("lng"));
					pharmacyList.add(new MyPharmacy(id, name, adress, tel, lat, lng));

				}
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#deleteMyPharmacy(int)
	 */
	@Override
	public boolean deleteMyPharmacy(int id)
	{
		// TODO remove from xml
		return true;
	}

	@Override
	public boolean exist(int idPharmacy)
	{
		for (MyPharmacy pharmacy : pharmacyList)
		{
			if (pharmacy.getId() == idPharmacy)
				return true;
		}
		return false;

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#insertMyPharmacy(be.itsworking.dpl.MyPharmacy)
	 */
	@Override
	public boolean insertMyPharmacy(MyPharmacy myPharmacy)
	{
		if (exist(myPharmacy.getId()))
			return updateMyPharmacy(myPharmacy);
		// TODO insert in xml;
		return false;
	}

	@Override
	public boolean insertMyPharmacyList(ArrayList<MyPharmacy> listToInsert)
	{
		boolean ok = true;

		for (MyPharmacy myPharmacy : listToInsert)
		{
			ok = insertMyPharmacy(myPharmacy);
		}
		return ok;

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#updateMyPharmacy(be.itsworking.dpl.MyPharmacy)
	 */
	@Override
	public boolean updateMyPharmacy(MyPharmacy myPharmacy)
	{
		if (!exist(myPharmacy.getId()))
			return insertMyPharmacy(myPharmacy);
		// TODO update in xml;
		return false;

	}

	/**
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#loadMyPharmacy(int)
	 */
	@Override
	public MyPharmacy loadMyPharmacy(int id)
	{
		for (MyPharmacy pharmacy : pharmacyList)
		{
			if (pharmacy.getId() == id)
				return pharmacy;
		}
		return null;

	}

	/**
	 * 
	 * @see be.itsworking.dpl.dao.DAOMyPharmacy#selectAllPharmacies()
	 */
	@Override
	public ArrayList<MyPharmacy> selectAllPharmacies()
	{
		return pharmacyList;
	}

	@Override
	public int countAll()
	{
		return pharmacyList.size();
	}

}
