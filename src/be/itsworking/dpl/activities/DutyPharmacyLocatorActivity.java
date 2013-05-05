package be.itsworking.dpl.activities;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import be.itsworking.dpl.R;

public class DutyPharmacyLocatorActivity extends TabActivity
{
	TabHost mTabHost;
	FrameLayout mFrameLayout;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Context ctx = this.getApplicationContext();

		setContentView(R.layout.main);
		mTabHost = getTabHost();

		// Map Tab
		TabSpec tabSpec = mTabHost.newTabSpec("map");
		tabSpec.setIndicator("Map", getResources().getDrawable(R.drawable.androidmarker));
		tabSpec.setContent(new Intent(ctx, MyMapActivity.class));
		mTabHost.addTab(tabSpec);

		// List Tab
		TabSpec tabSpec2 = mTabHost.newTabSpec("list");
		tabSpec2.setIndicator("List", getResources().getDrawable(R.drawable.blue));
		tabSpec2.setContent(new Intent(ctx, MyListActivity.class));
		mTabHost.addTab(tabSpec2);

		// Pharmacy Tab
		TabSpec tabSpec3 = mTabHost.newTabSpec("pharmacy");
		tabSpec3.setIndicator("Pharmacy", getResources().getDrawable(R.drawable.caducee));
		tabSpec3.setContent(new Intent(ctx, MyPharmacyActivity.class));
		mTabHost.addTab(tabSpec3);

		mTabHost.setCurrentTab(0);
	}
}
