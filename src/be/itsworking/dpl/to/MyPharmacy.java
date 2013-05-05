package be.itsworking.dpl.to;

import java.sql.Timestamp;
import java.util.Calendar;

import android.location.Location;

import com.google.android.maps.GeoPoint;

public class MyPharmacy
{
	private int id;
	private String nom;
	private String adresse;
	private String tel;
	private Location location;
	private boolean isOpen;
	private Timestamp insertTS;
	private Timestamp updateTS;

	public MyPharmacy(int id, String nom, Location location, boolean isOpen)
	{
		this.id = id;
		this.nom = nom;
		this.location = location;
		this.isOpen = isOpen;
		this.insertTS = new Timestamp(Calendar.getInstance().getTimeInMillis());
		this.updateTS = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}
	
	public int getId()
	{
		return id;
	}

	public MyPharmacy(int id, double lat, double lng, String string)
	{
		this.nom = ""+id;
		this.id=id;
		this.location = new Location(string);
		this.location.setLatitude(lat);
		this.location.setLongitude(lng);
	}

	public GeoPoint getGeoPoint()
	{
		return new GeoPoint((int) (location.getLatitude() * 1E6), (int) (location.getLongitude() * 1E6));
	}

	public String toCSV()
	{
		String str = "";

		str += id + ";";
		str += nom + ";";
		str += adresse + ";";
		str += tel + ";";
		str += location.getLatitude() + ";";
		str += location.getLongitude() + ";";
		return str;
	}
	
	public String getNom()
	{
		return nom+"("+id+")";
	}

	/**
	 * @return the location
	 */
	public Location getLocation()
	{
		return location;
	}

	/**
	 * @return the isOpen
	 */
	public boolean isOpen()
	{
		return isOpen;
	}

	/**
	 * @return the insertTS
	 */
	public Timestamp getInsertTS()
	{
		return insertTS;
	}

	/**
	 * @return the updateTS
	 */
	public Timestamp getUpdateTS()
	{
		return updateTS;
	}

	/**
	 * @param nom the nom to set
	 */
	public void setNom(String nom)
	{
		this.nom = nom;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * @param isOpen the isOpen to set
	 */
	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	/**
	 * @param updateTS the updateTS to set
	 */
	public void setUpdateTS(Timestamp updateTS)
	{
		this.updateTS = updateTS;
	}

	public String getAdresse()
	{
		return adresse;
	}
	
	
	public String getTel()
	{
		return tel;
	}
	
	
}
