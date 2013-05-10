package be.itsworking.dpl.to;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

public class MyPharmacy
{
	private int id;
	private String name;
	private String adresse;
	private String tel;
	private Location location;
	private boolean isOpen;
	private Timestamp insertTS;
	private Timestamp updateTS;
	private LatLng latlng;

	public MyPharmacy(int id, String nom, String address, String tel, double lat, double lng)
	{
		this.id = id;
		this.name = nom;
		this.adresse = address;
		this.tel = tel;

		this.location = new Location("DB");
		this.location.setLatitude(lat);
		this.location.setLongitude(lng);
		
		this.latlng = new LatLng(lat, lng);

		this.isOpen = new Random().nextBoolean();
		this.insertTS = new Timestamp(Calendar.getInstance().getTimeInMillis());
		this.updateTS = new Timestamp(Calendar.getInstance().getTimeInMillis());
	}

	
	/**
	 * @return the id
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the adresse
	 */
	public String getAdresse()
	{
		return adresse;
	}

	/**
	 * @return the tel
	 */
	public String getTel()
	{
		return tel;
	}

	/**
	 * @return the latlng
	 */
	public LatLng getLatlng()
	{
		return latlng;
	}


	/**
	 * @param latlng the latlng to set
	 */
	public void setLatlng(LatLng latlng)
	{
		this.latlng = latlng;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * @param nom
	 *            the name to set
	 */
	public void setName(String nom)
	{
		this.name = nom;
	}

	/**
	 * @param adresse
	 *            the adresse to set
	 */
	public void setAdresse(String adresse)
	{
		this.adresse = adresse;
	}

	/**
	 * @param tel
	 *            the tel to set
	 */
	public void setTel(String tel)
	{
		this.tel = tel;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(Location location)
	{
		this.location = location;
	}

	/**
	 * @param isOpen
	 *            the isOpen to set
	 */
	public void setOpen(boolean isOpen)
	{
		this.isOpen = isOpen;
	}

	/**
	 * @param insertTS
	 *            the insertTS to set
	 */
	public void setInsertTS(Timestamp insertTS)
	{
		this.insertTS = insertTS;
	}

	/**
	 * @param updateTS 
	 *            the updateTS to set
	 */
	public void setUpdateTS(Timestamp updateTS)
	{
		this.updateTS = updateTS;
	}

}
