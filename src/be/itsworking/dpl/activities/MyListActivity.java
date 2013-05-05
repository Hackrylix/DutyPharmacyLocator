package be.itsworking.dpl.activities;

import java.util.ArrayList;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import be.itsworking.dpl.MyPharmacyManager;
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

	@Override
	protected void onResume()
	{
		refresh();
		super.onResume();
	}

	private void refresh()
	{
		

		LinearLayout ll = new LinearLayout(getApplicationContext());
		ScrollView sc = new ScrollView(getApplicationContext());
		
		ll.setOrientation(LinearLayout.VERTICAL);

		ArrayList<MyPharmacy> list = myPharmacyManager.getPharmacyList();
		for (int i = 0; i < list.size(); i++)
		{
			MyPharmacy pharmacy = list.get(i);
			TextView tv = new TextView(getApplicationContext());
			tv.setText(pharmacy.toCSV(), BufferType.NORMAL);
			tv.setLongClickable(true);
			tv.setOnLongClickListener(this);
			ll.addView(tv);
		}
		
		sc.addView(ll);
		setContentView(sc);

	}

	

	@Override
	public boolean onLongClick(View v)
	{
		if (v instanceof TextView)
		{
			TextView tv = (TextView) v;
			String[] arr = ((String) tv.getText().toString()).split(";");
			Intent mi = new Intent(this, MyPharmacyActivity.class);
			mi.putExtra("PHARMACY_ID", arr[0]);
			
			startActivity(mi);
			return true;
		}

		return false;
	}

	private void log(String string)
	{
		System.out.println(string);

	}

}
