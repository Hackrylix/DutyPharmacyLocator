package be.itsworking.dpl;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import be.itsworking.dpl.daoSQLite.DAOMyPharmacySQLite;
import be.itsworking.dpl.to.MyPharmacy;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.GeoPoint;

public class MyPharmacyManager
{
	private ArrayList<MyPharmacy> pharmacyList;

	private DAOMyPharmacySQLite pharmacyDAO;
	private Activity activity;
	private MyLocationManager myLocationManager;
	private MyPharmacy nearest;

	
	public MyPharmacyManager(Activity app, boolean track)
	{
		activity = app;

		pharmacyList = new ArrayList<MyPharmacy>();

		pharmacyDAO = new DAOMyPharmacySQLite(app);
		myLocationManager = new MyLocationManager(app, track);
		loadAll();

	}

	public boolean loadAll()
	{
		pharmacyList = pharmacyDAO.selectAllPharmacies();
		log("Pharmacies Loaded");
		return true;

	}

	public MyPharmacy getNearestPharmacy()
	{

		if (nearest == null)
			findNearestPharmacy();
		return nearest;
	}

	private void findNearestPharmacy()
	{
		float min = 99999999;
		for (int i = 0; i < pharmacyList.size(); i++)
		{
			MyPharmacy cp = pharmacyList.get(i);
			float dist = myLocationManager.getCurrentLocation().distanceTo(cp.getLocation());
			if (dist < min)
			{
				min = dist;
				nearest = cp;
			}
		}

	}

	public boolean saveAll()
	{
		if (pharmacyDAO.insertMyPharmacyList(pharmacyList))
		{
			log("Positions Saved");
			return true;
		}
		else
			return false;

	}

	private void log(String string)
	{
		System.out.println(string);
		Util.showMessage(activity, string);

	}

	public ArrayList<MyPharmacy> getPharmacyList()
	{
		return pharmacyList;
	}

	public boolean isInList(MyPharmacy ml)
	{
		for (int i = 0; i < pharmacyList.size(); i++)
		{
			if (pharmacyList.get(i).equals(ml))
				return true;
		}
		return false;
	}

	public GeoPoint getCurrentGeoPoint()
	{
		return myLocationManager.getCurrentGeoPoint();
	}

	public Location getCurrentLocation()
	{
		return myLocationManager.getCurrentLocation();
	}

	public boolean delete(String string)
	{
		return pharmacyDAO.deleteMyPharmacy(Integer.valueOf(string));
	}
	
	public boolean delete(int id)
	{
		return pharmacyDAO.deleteMyPharmacy(id);
	}

	public void setTrack(boolean isChecked)
	{
		myLocationManager.setTrack(isChecked);
	}

	public void requestUpdate()
	{
		myLocationManager.requestUpdate();
	}

	public MyPharmacy getPharmacy(int id)
	{
		for (int i = 0; i < pharmacyList.size(); i++)
		{
			MyPharmacy cp = pharmacyList.get(i);
			if(cp.getId()==id)
				return cp;
		}
		return null;

	}

}
