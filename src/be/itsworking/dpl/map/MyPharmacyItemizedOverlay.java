/**
 * 
 */
package be.itsworking.dpl.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import be.itsworking.dpl.R;
import be.itsworking.dpl.activities.MyPharmacyActivity;

/**
 * @author hackrylix
 * 
 */
public class MyPharmacyItemizedOverlay extends MyItemizedOverlay
{

	/**
	 * @param defaultMarker
	 * @param activity
	 */
	public MyPharmacyItemizedOverlay(Drawable defaultMarker, Activity activity)
	{
		super(defaultMarker, activity);
		// TODO Auto-generated constructor stub

	}

	public MyPharmacyItemizedOverlay(Activity activity)
	{
		super(activity.getResources().getDrawable(R.drawable.caducee), activity);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean onTap(int index)
	{
		Intent myPharmacyActivity = new Intent(activity, MyPharmacyActivity.class);
		myPharmacyActivity.putExtra("PHARMACY_ID", olverlayList.get(index).getTitle());
		activity.startActivityForResult(myPharmacyActivity, 1);
		return true;
	}

}
