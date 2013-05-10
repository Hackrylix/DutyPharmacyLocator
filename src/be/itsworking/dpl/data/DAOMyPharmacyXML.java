/**
 * 
 */
package be.itsworking.dpl.data;

import java.io.IOException;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParserException;

import be.itsworking.dpl.to.MyPharmacy;

public class DAOMyPharmacyXML
{

	public static MyPharmacy loadMyPharmacy(int id)
	{
		ArrayList<MyPharmacy> pharmacyList;
		try
		{

			pharmacyList = new PharmacyXMLParser().parse();
			for (MyPharmacy pharmacy : pharmacyList)
			{
				if (pharmacy.getId() == id)
					return pharmacy;
			}
		}
		catch (XmlPullParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static MyPharmacy loadMyPharmacy(String name)
	{
		ArrayList<MyPharmacy> pharmacyList;
		try
		{
			pharmacyList = new PharmacyXMLParser().parse();
			for (MyPharmacy pharmacy : pharmacyList)
			{
				if (pharmacy.getName().equals(name))
					return pharmacy;
			}
			return null;
		}
		catch (XmlPullParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public static ArrayList<MyPharmacy> selectAllPharmacies()
	{

		ArrayList<MyPharmacy> pharmacyList;
		try
		{
			pharmacyList = new PharmacyXMLParser().parse();
			return pharmacyList;
		}
		catch (XmlPullParserException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
