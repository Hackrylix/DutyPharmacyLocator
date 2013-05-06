package be.itsworking.dpl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.MyPharmacyManager;
import be.itsworking.dpl.R;
import be.itsworking.dpl.to.MyPharmacy;

public class MyListActivity extends Activity implements OnLongClickListener
{

	private MyPharmacyManager myPharmacyManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		myPharmacyManager = new MyPharmacyManager(this,false);
		refresh();

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
		refresh();
		super.onResume();
	}

	private void refresh()
	{
		LinearLayout linearLayout = new LinearLayout(getApplicationContext());
		ScrollView scrollView = new ScrollView(getApplicationContext());
		
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		for (MyPharmacy pharmacy : myPharmacyManager.getPharmacyList())
		{
			TextView textView = new TextView(getApplicationContext());
			textView.setText(String.valueOf(pharmacy.getId()), BufferType.NORMAL);
			textView.setLongClickable(true);
			textView.setOnLongClickListener(this);
			linearLayout.addView(textView);
		}
		
		scrollView.addView(linearLayout);
		setContentView(scrollView);

	}


	@Override
	public boolean onLongClick(View view)
	{
		if (view instanceof TextView)
		{
			TextView textView = (TextView) view;
			Intent myPharmacyIntent = new Intent(this, MyPharmacyActivity.class);
			myPharmacyIntent.putExtra("PHARMACY_ID", textView.getText().toString());
			
			startActivity(myPharmacyIntent);
			return true;
		}

		return false;
	}

}
