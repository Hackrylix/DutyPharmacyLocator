package be.itsworking.dpl;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks;
import com.google.android.gms.common.GooglePlayServicesClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * @author samary
 */
public class DutyPharmacyLocatorActivity extends TabActivity implements ConnectionCallbacks, OnConnectionFailedListener
{

    private LocationClient locationClient;
    private boolean isSet = false;
    private ArrayList<MyPharmacy> pharmacyList=null;


    /*
     * (non-Javadoc)
     *
     * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
     */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_duty_pharmacy_locator);

        locationClient = new LocationClient(this, this, this);

    }



    /*
     * Called when the Activity becomes visible.
     */
    /*
     * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onStart()
	 */
    @Override
    protected void onStart()
    {
        super.onStart();
        // Connect the client.
        locationClient.connect();
    }

    /*
     * Called when the Activity is no longer visible.
     */
    /*
	 * (non-Javadoc)
	 * 
	 * @see android.app.ActivityGroup#onStop()
	 */
    @Override
    protected void onStop()
    {
        // Disconnecting the client invalidates it.
        locationClient.disconnect();
        super.onStop();
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks
     * #onConnected(android.os.Bundle)
     */
    @Override
    public void onConnected(Bundle arg0)
    {
        if(pharmacyList==null)
        {
        Location location = locationClient.getLastLocation();
        if (location != null)
        {
            LatLng currentLocation = new LatLng(location.getLatitude(), location.getLongitude());
            pharmacyList = PharmacyList.getList(currentLocation);
            if(pharmacyList==null)
            {
                Toast.makeText(getApplicationContext(), "Pharmacy List is null",Toast.LENGTH_LONG).show();
            }
            else
            {
                if (!isSet)
                    setupActivities();
            }
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Cannot get location !!",Toast.LENGTH_LONG).show();
            Log.e("location on connected", "location=null");
        }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * com.google.android.gms.common.GooglePlayServicesClient.ConnectionCallbacks
     * #onDisconnected()
     */
    @Override
    public void onDisconnected()
    {
        Log.e("Connection Location", "disconnected");

    }

    /*
     * (non-Javadoc)
     *
     * @see com.google.android.gms.common.GooglePlayServicesClient.
     * OnConnectionFailedListener
     * #onConnectionFailed(com.google.android.gms.common.ConnectionResult)
     */
    @Override
    public void onConnectionFailed(ConnectionResult arg0)
    {
        Log.e("Connection Location", "failed");
    }

    /**
     *
     */
    protected void setupActivities()
    {
        isSet = true;
        TabHost tabs = getTabHost();

        // Map Tab
        TabSpec mapTab = tabs.newTabSpec("map");
        mapTab.setIndicator(getString(R.string.mapText));

        Intent mapIntent = new Intent(this, MyMapActivity.class);
        mapIntent.putExtra("pl", pharmacyList);
        mapTab.setContent(mapIntent);
        tabs.addTab(mapTab);

        // List Tab
        TabSpec listTab = tabs.newTabSpec("list");
        listTab.setIndicator(getString(R.string.listTest));

        Intent listIntent = new Intent(this, MyListActivity.class);
        listIntent.putExtra("pl", pharmacyList);
        listTab.setContent(listIntent);
        tabs.addTab(listTab);

        tabs.setCurrentTab(0);
    }

    /*
    * (non-Javadoc)
    *
    * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle item selection
        switch (item.getItemId())
        {
            case R.id.quit:
                finish();
                return true;
            case R.id.info:
                // 1. Instantiate an AlertDialog.Builder with its constructor
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

            // 2. Chain together various setter methods to set the dialog characteristics
                builder.setMessage(getString(R.string.devby));
                builder.setTitle("Information");

                // 3. Get the AlertDialog from create()
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}

/*
 * LocationManager locationManager = (LocationManager)
 * this.getSystemService(Context.LOCATION_SERVICE);
 * 
 * if (locationManager != null) { Location loc =
 * locationManager.getLastKnownLocation (LocationManager.PASSIVE_PROVIDER);
 * currentPosition = new LatLng(loc.getLatitude(), loc.getLongitude());
 * 
 * ConnectivityManager cm = (ConnectivityManager)
 * getApplication().getSystemService(Context.CONNECTIVITY_SERVICE); if (cm !=
 * null) { NetworkInfo activeNetwork = cm.getActiveNetworkInfo(); if
 * (activeNetwork != null && activeNetwork.isConnectedOrConnecting())
 * setList(FROM_NETWORK); else setList(FROM_DATABASE); } else
 * setList(FROM_DATABASE); } else setList(FROM_DATABASE);
 */
