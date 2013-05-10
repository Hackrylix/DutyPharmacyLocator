package be.itsworking.dpl.activities;

import java.util.ArrayList;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.R;
import be.itsworking.dpl.data.DAOMyPharmacyXML;
import be.itsworking.dpl.to.MyPharmacy;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MyMapActivity extends FragmentActivity implements OnMyLocationChangeListener, OnInfoWindowClickListener
{
	
	private GoogleMap map = null;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_map);
		setUpMapIfNeeded();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		setUpMapIfNeeded();
	}

	private void setUpMapIfNeeded()
	{
		if (map == null)
		{
			map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			if (map != null)
			{
				setUpMap();
			}
		}

	}

	private void setUpMap()
	{
		
		UiSettings settings = map.getUiSettings();
		settings.setAllGesturesEnabled(true);
		map.animateCamera(CameraUpdateFactory.zoomTo(16));
		map.setMyLocationEnabled(true);
		map.setOnInfoWindowClickListener(this);
		map.setOnMyLocationChangeListener(this);
		ArrayList<MyPharmacy> list = DAOMyPharmacyXML.selectAllPharmacies();
		for (MyPharmacy pharmacy : list)
		{
			MarkerOptions markerOption = new MarkerOptions();

			markerOption.title(pharmacy.getName());
			markerOption.snippet(pharmacy.getAdresse());
			markerOption.position(pharmacy.getLatlng());
			
			if (pharmacy.isOpen())
				markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.caduceus));
			else
				markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.caducee));

			map.addMarker(markerOption);
		}
	}
	
	@Override
	public void onInfoWindowClick(Marker marker)
	{
		Intent myPharmacyIntent = new Intent(this, MyPharmacyActivity.class);
		myPharmacyIntent.putExtra("PHARMACY_NAME", marker.getTitle());
		startActivity(myPharmacyIntent);
	}

	@Override
	public void onMyLocationChange(Location location)
	{
		log("new loc :" + location.getLatitude() + "-" + location.getLongitude());
		LatLng currLatLng = new LatLng(location.getLatitude(), location.getLongitude());
		map.animateCamera(CameraUpdateFactory.newLatLng(currLatLng));
	}
	
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.mainmenu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		// Handle item selection
		switch (item.getItemId())
		{
			case R.id.item1:
				finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	void log(String str)
	{
		System.out.println(str);
		TextView log = (TextView) findViewById(R.id.log);
		log.setText(str, BufferType.EDITABLE);
		//Util.showMessage(getApplicationContext(), str);
	}

}
