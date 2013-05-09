package be.itsworking.dpl;

import java.io.Serializable;
import java.util.ArrayList;

import be.itsworking.dpl.dao.DAOMyPharmacyXML;
import be.itsworking.dpl.to.MyPharmacy;

@SuppressWarnings("serial")
public class MyPharmacyManager implements Serializable
{
	private ArrayList<MyPharmacy> pharmacyList;

	/**
	 * 
	 */
	public MyPharmacyManager()
	{
		pharmacyList = new DAOMyPharmacyXML().selectAllPharmacies();
	}

	/**
	 * @return {@link ArrayList}
	 */
	public ArrayList<MyPharmacy> getPharmacyList()
	{
		return pharmacyList;
	}

	/**
	 * @param id
	 * @return
	 */
	public MyPharmacy getPharmacy(int id)
	{
		for (MyPharmacy pharmacy : pharmacyList)
		{
			if (pharmacy.getId() == id)
				return pharmacy;
		}
		return null;
	}
	
	
}
