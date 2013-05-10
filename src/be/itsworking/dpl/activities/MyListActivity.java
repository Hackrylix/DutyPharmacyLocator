package be.itsworking.dpl.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.R;
import be.itsworking.dpl.data.DAOMyPharmacyXML;
import be.itsworking.dpl.to.MyPharmacy;

public class MyListActivity extends Activity implements OnClickListener
{

	private LinearLayout linearLayout = null;
	private ScrollView scrollView = null;

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		if (linearLayout == null)
			linearLayout = new LinearLayout(getApplicationContext());
		if (scrollView == null)
			scrollView = new ScrollView(getApplicationContext());

		linearLayout.setOrientation(LinearLayout.VERTICAL);
		if (linearLayout.getChildCount() == 0)
		{
			for (MyPharmacy pharmacy : DAOMyPharmacyXML.selectAllPharmacies())
			{
				TextView textView = new TextView(getApplicationContext());
				textView.setText(pharmacy.getName(), BufferType.NORMAL);
				textView.setClickable(true);
				textView.setOnClickListener(this);
				linearLayout.addView(textView);
			}
		}

		scrollView.addView(linearLayout);
		setContentView(scrollView);
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
			case R.id.item1:
				finish();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View view)
	{
		if (view instanceof TextView)
		{
			TextView textView = (TextView) view;
			Intent myPharmacyIntent = new Intent(this, MyPharmacyActivity.class);
			myPharmacyIntent.putExtra("PHARMACY_NAME", textView.getText().toString());
			startActivity(myPharmacyIntent);
		}
	}

}
