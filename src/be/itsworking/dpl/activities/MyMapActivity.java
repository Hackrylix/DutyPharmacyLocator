package be.itsworking.dpl.activities;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import android.widget.ToggleButton;
import be.itsworking.dpl.MyPharmacyManager;
import be.itsworking.dpl.R;
import be.itsworking.dpl.map.MyLocationItemizedOverlay;
import be.itsworking.dpl.map.MyPharmacyItemizedOverlay;
import be.itsworking.dpl.to.MyPharmacy;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

public class MyMapActivity extends MapActivity
{

	private MyPharmacyManager myPharmacyManager;
	private MapView mapView;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		setContentView(R.layout.maptabview);

		mapView = (MapView) findViewById(R.id.mapview);

		ToggleButton track = (ToggleButton) findViewById(R.id.trackButton);
		myPharmacyManager = new MyPharmacyManager(this, track.isChecked());

		track.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
			{
				myPharmacyManager.setTrack(isChecked);
			}
		});

		Button refresh = (Button) findViewById(R.id.refreshButton);
		refresh.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v)
			{
				myPharmacyManager.requestUpdate();

			}
		});

		// myPharmacyManager.loadAll();
		// refreshMap();
		// myPharmacyManager.saveAll(this);

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

	@Override
	protected void onResume()
	{
		super.onResume();
	}

	@Override
	protected void onStop()
	{
		super.onStop();
	}

	public void refreshMap()
	{
		mapView.getOverlays().clear();

		Drawable drawableCaduceus = getResources().getDrawable(
				R.drawable.caducee);
		drawableCaduceus.setBounds(-drawableCaduceus.getIntrinsicWidth() / 2,
				-drawableCaduceus.getIntrinsicHeight(), drawableCaduceus
						.getIntrinsicWidth() / 2, 0);
		MyPharmacyItemizedOverlay itemizedoverlay = new MyPharmacyItemizedOverlay(
				drawableCaduceus, this);

		ArrayList<MyPharmacy> list = myPharmacyManager.getPharmacyList();
		for (int i = 0; i < list.size(); i++)
		{
			MyPharmacy pharmacy = list.get(i);
			GeoPoint gp = pharmacy.getGeoPoint();

			OverlayItem oi = new OverlayItem(gp, String.valueOf(pharmacy
					.getId()), pharmacy.getAdresse());
			oi.setMarker(drawableCaduceus);
			itemizedoverlay.addOverlay(oi);
		}

		mapView.getOverlays().add(itemizedoverlay);

		GeoPoint currentGP = myPharmacyManager.getCurrentGeoPoint();
		Drawable drawable2 = getResources().getDrawable(
				R.drawable.androidmarkerred);
		drawable2.setBounds(-drawable2.getIntrinsicWidth() / 2, -drawable2
				.getIntrinsicHeight(), drawable2.getIntrinsicWidth() / 2, 0);
		MyLocationItemizedOverlay itemizedoverlay2 = new MyLocationItemizedOverlay(
				drawable2, this);
		OverlayItem overI = new OverlayItem(currentGP, myPharmacyManager
				.getCurrentLocation().toString(), "Position actuelle");
		overI.setMarker(drawable2);
		itemizedoverlay2.addOverlay(overI);

		mapView.getOverlays().add(itemizedoverlay2);
		mapView.setBuiltInZoomControls(true);
		// mapView.setSaveEnabled(true);
		mapView.getController().animateTo(currentGP);
		mapView.setSatellite(false);
		// mapView.getController().setZoom(mapView.getMaxZoomLevel() - 2);
		mapView.postInvalidate();

		log("map refreshed");
	}

	@Override
	public boolean onSearchRequested()
	{
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

		return super.onSearchRequested();
	}

	@Override
	protected boolean isRouteDisplayed()
	{
		return false;
	}

	void log(String str)
	{
		System.out.println(str);
		TextView log = (TextView) findViewById(R.id.log);
		log.setText(str, BufferType.EDITABLE);
		Util.showMessage(getApplicationContext(), str);
	}

}
