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
	TabHost tabs;
	FrameLayout mFrameLayout;

	/** Called when the activity is first created. */
	/* (non-Javadoc)
	 * @see android.app.ActivityGroup#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		Context ctx = this.getApplicationContext();
				
		setContentView(R.layout.activity_duty_pharmacy_locator);
		tabs = getTabHost();

		// Map Tab
		TabSpec mapTab = tabs.newTabSpec("map");
		mapTab.setIndicator("Map");
		Intent mapIntent = new Intent(ctx, MyMapActivity.class);
		mapTab.setContent(mapIntent);
		tabs.addTab(mapTab);

		// List Tab
		TabSpec listTab = tabs.newTabSpec("list");
		listTab.setIndicator("List");
		Intent listIntent = new Intent(ctx, MyListActivity.class);
		listTab.setContent(listIntent);
		tabs.addTab(listTab);

		tabs.setCurrentTab(0);
	}
}
