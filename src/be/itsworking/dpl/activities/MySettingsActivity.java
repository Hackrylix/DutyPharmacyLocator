package be.itsworking.dpl.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import be.itsworking.dpl.R;

public class MySettingsActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_settings);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu)
	{
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_settings, menu);
		return true;
	}

}
