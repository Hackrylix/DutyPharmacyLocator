package be.itsworking.dpl;

import java.util.ArrayList;

import android.app.Activity;
import android.location.Location;
import be.itsworking.dpl.dao.DAOMyPharmacyXML;
import be.itsworking.dpl.to.MyPharmacy;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.GeoPoint;

public class MyPharmacyManager
{
	private ArrayList<MyPharmacy> pharmacyList;

	private Activity activity;
	private MyLocationManager myLocationManager;
	private MyPharmacy nearest;

	
	public MyPharmacyManager(Activity app, boolean track)
	{
		activity = app;
		pharmacyList = new DAOMyPharmacyXML(activity).selectAllPharmacies();
		myLocationManager = new MyLocationManager(activity, track);
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
		for (MyPharmacy pharmacy : pharmacyList)
		{
			float dist = myLocationManager.getCurrentLocation().distanceTo(pharmacy.getLocation());
			if (dist < min)
			{
				min = dist;
				nearest = pharmacy;
			}
		}

	}

	public ArrayList<MyPharmacy> getPharmacyList()
	{
		return pharmacyList;
	}

	public GeoPoint getCurrentGeoPoint()
	{
		return myLocationManager.getCurrentGeoPoint();
	}

	public Location getCurrentLocation()
	{
		return myLocationManager.getCurrentLocation();
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
		for (MyPharmacy pharmacy : pharmacyList)
		{
			if(pharmacy.getId()==id)
				return pharmacy;
		}
		return null;
	}

}
