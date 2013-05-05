package be.itsworking.dpl.to;

import android.location.Location;
import android.net.NetworkInfo;

import com.google.android.maps.GeoPoint;

public class MyLocation extends Location
{
	
	private int id;
	private NetworkInfo net;
	public MyLocation(Location l)
	{
		super(l);
		id=0;
		
	}
	public void setNetwork(NetworkInfo net)
	{
		this.net = net;
	}

	public MyLocation(String line)
	{
		super("CACHE");
		String[] arr = line.split(";");
		this.id=Integer.valueOf(arr[0]);
		this.setLatitude(Double.valueOf(arr[1]));
		this.setLongitude(Double.valueOf(arr[2]));
		
	}
	
	public MyLocation(int id, double lat, double lng, String prov)
	{
		super(prov);
		
		this.id=id;
		this.setLatitude(lat);
		this.setLongitude(lng);
		
		
	}

	public String getData()
	{
		String str= id+";"+getLatitude()+";"+getLongitude();
		return str;
	}

	public boolean equals(MyLocation ml)
	{
		if (getLatitude() == ml.getLatitude() && getLongitude() == ml.getLongitude())
			return true;
		else
			return false;
	}

	public GeoPoint getGeoPoint()
	{
		return new GeoPoint((int)(getLatitude() *1E6),(int) (getLongitude()*1E6 ));

	}
	
	@Override
	public String toString()
	{
		// TODO Auto-generated method stub
		return getData();
		
	}
	public NetworkInfo getNet()
	{
		return net;
	}

	public int getId()
	{
		return this.id;
	}

}
