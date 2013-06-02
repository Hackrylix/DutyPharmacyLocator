package be.itsworking.dpl;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * @author samary
 */
public class MyMapActivity extends FragmentActivity implements OnInfoWindowClickListener
{

    private GoogleMap map = null;
   // private Location previousLocation;
    private ArrayList<MyPharmacy> pharmacyList;

    /**
     * Called when the activity is first created.
     */
    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onCreate(android.os.Bundle)
	 */
    @SuppressWarnings("unchecked")
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       Intent sender = getIntent();
        Bundle extras = sender.getExtras();
        Object object = extras.get("pl");
        pharmacyList = new ArrayList<MyPharmacy>();
        if (object != null && object instanceof ArrayList)
        {
            pharmacyList = (ArrayList<MyPharmacy>) object;
            setContentView(R.layout.activity_my_map);
            setUpMapIfNeeded();
        }
        else
            new Error("not instance of arraylist").printStackTrace();

    }

    /* (non-Javadoc)
     * @see android.support.v4.app.FragmentActivity#onResume()
     */
    @Override
    protected void onResume()
    {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     *
     */
    private void setUpMapIfNeeded()
    {
        if (map == null)
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
        {if(map!=null)
            setUpMap();
        else
            Log.e("Cannot get map","map == null");}


    }

    /**
     *
     */
    private void setUpMap()
    {

        UiSettings settings = map.getUiSettings();
        settings.setAllGesturesEnabled(true);
        map.animateCamera(CameraUpdateFactory.zoomTo(13));
        map.setMyLocationEnabled(true);
        map.setOnInfoWindowClickListener(this);
        //map.setOnMyLocationChangeListener(this);

        for (MyPharmacy pharmacy : pharmacyList)
        {
            MarkerOptions markerOption = new MarkerOptions();

            markerOption.title(pharmacy.getName());
            markerOption.snippet(pharmacy.getAdresse() + " " + pharmacy.getCp());
            markerOption.position(pharmacy.getLatlng());

            markerOption.icon(BitmapDescriptorFactory.fromResource(R.drawable.caduceus));

            map.addMarker(markerOption);
        }
       Location pos = map.getMyLocation();
        if(pos!=null)
        map.animateCamera(CameraUpdateFactory.newLatLng(new LatLng(pos.getLatitude() ,pos.getLongitude())));
    }

    /* (non-Javadoc)
     * @see com.google.android.gms.maps.GoogleMap.OnInfoWindowClickListener#onInfoWindowClick(com.google.android.gms.maps.model.Marker)
     */
    @Override
    public void onInfoWindowClick(Marker marker)
    {
        String pharmacyName = marker.getTitle();

                Intent myPharmacyIntent = new Intent(this, MyPharmacyActivity.class);
                myPharmacyIntent.putExtra("pharmacy", PharmacyList.getPharmacy(pharmacyName));
                startActivity(myPharmacyIntent);



    }

    /* (non-Javadoc)
     * @see com.google.android.gms.maps.GoogleMap.OnMyLocationChangeListener#onMyLocationChange(android.location.Location)
     *//*
    @Override
    public void onMyLocationChange(Location location)
    {

       if(pharmacyList==null)
        {
            Intent sender = getIntent();
            Bundle extras = sender.getExtras();
            Object pl = extras.get("pl");
            pharmacyList = new ArrayList<MyPharmacy>();
            if (pl instanceof ArrayList)
            {
                pharmacyList = (ArrayList<MyPharmacy>) pl;
            }
            else
                new Error("not instance of arraylist").printStackTrace();
        }

        if (previousLocation == null)
            previousLocation = new Location("EMPTY");
        if (!location.equals(previousLocation))
        {
            log("new loc : " + location.getLatitude() + "-" + location.getLongitude() + "-" + location.getAccuracy());
            LatLng currLatLng = new LatLng(location.getLatitude(), location.getLongitude());

            if (location.getAccuracy() < previousLocation.getAccuracy())
                map.animateCamera(CameraUpdateFactory.newLatLng(currLatLng));
            previousLocation = location;

            // ArrayList<MyPharmacy> templist = MyPharmacySync.getList(new
            // LatLng(location.getLatitude(), location.getLongitude()));
            // if (templist != null && templist.size() > 0 &&
            // !templist.equals(pharmacyList))
            // {
            // pharmacyList = templist;
            // log("new list :" + pharmacyList.size());
            // setUpMap();
            // }
        }
        else
            log("same location");

    }*/


}
