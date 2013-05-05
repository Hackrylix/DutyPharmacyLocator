package be.itsworking.dpl;

import android.app.Activity;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import be.itsworking.dpl.activities.MyMapActivity;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.GeoPoint;

public class MyLocationManager implements LocationListener
{
	private Location currentLocation;

	private Activity activity;
	private LocationManager lm;
	private String provider;
	private int minTime;
	private int metre;
	private boolean track;

	public MyLocationManager(Activity app, boolean track)
	{
		this.activity = app;
		this.track = track;
		this.lm = (LocationManager) app.getSystemService(Context.LOCATION_SERVICE);
		selectBestProvider();
		this.minTime = 1000;
		this.metre = 1;
		getLastKnown();
		initListener();

	}

	private void selectBestProvider()
	{
		Criteria criteria = new Criteria();
		criteria.setAltitudeRequired(false);
		criteria.setAccuracy(Criteria.ACCURACY_FINE);
		this.provider = lm.getBestProvider(criteria, true);

	}

	private void log(String string)
	{
		System.out.println(string);
		Util.showMessage(activity, string);

	}

	public GeoPoint getCurrentGeoPoint()
	{
		if (currentLocation == null)
			if (!getLastKnown())
				log("no last loc !!!");
		return new GeoPoint((int) (currentLocation.getLatitude() * 1E6), (int) (currentLocation.getLongitude() * 1E6));
	}

	public Location getCurrentLocation()
	{
		return currentLocation;
	}

	public boolean getLastKnown()
	{
		Location loc = lm.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
		if (loc == null)
			return false;
		else
		{
			currentLocation = loc;
			return true;
		}
	}

	@Override
	public void onLocationChanged(Location location)
	{

		updateLocation(location);
	}

	@Override
	public void onProviderDisabled(String provider)
	{
		stopListener();
		log("provider disabled " + provider);
		selectBestProvider();
	}

	@Override
	public void onProviderEnabled(String provider)
	{

		log("provider enabled " + provider);
		selectBestProvider();
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras)
	{
		switch (status)
		{
			case LocationProvider.AVAILABLE:
				log("status changed : " + provider + " is Available !");
				break;
			case LocationProvider.OUT_OF_SERVICE:
				log("status changed : " + provider + " is OOS !");
				break;
			case LocationProvider.TEMPORARILY_UNAVAILABLE:
				log("status changed : " + provider + " is temp not available !");
				break;
			default:
				log("status changed : " + provider);
				break;
		}
		selectBestProvider();

	}

	private void updateLocation(Location location)
	{
		currentLocation = location;
		if (activity instanceof MyMapActivity)
			((MyMapActivity) activity).refreshMap();
	}

	public void initListener()
	{
		if (lm == null)
			lm = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);

		if (track)
			lm.requestLocationUpdates(provider, minTime * 1000, metre, this);
		else
			lm.requestSingleUpdate(provider, this, null);

		log("listener init : " + track);
	}

	public void stopListener()
	{
		lm.removeUpdates(this);
		log("listener stop");
	}


	public String getProvider()
	{
		return this.provider;
	}

	public void requestUpdate()
	{
		lm.requestSingleUpdate(provider, this, null);
		log("update requested");
	}

	public void setTrack(boolean isChecked)
	{
		this.track = isChecked;

		resetListener();

	}

	private void resetListener()
	{
		stopListener();
		initListener();

	}
}
