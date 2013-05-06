/**
 * 
 */
package be.itsworking.dpl.map;

import be.itsworking.dpl.R;
import android.app.Activity;
import android.graphics.drawable.Drawable;

/**
 * @author hackrylix
 *
 */
public class MyLocationItemizedOverlay extends MyItemizedOverlay
{

	/**
	 * @param defaultMarker
	 * @param activity
	 */
	public MyLocationItemizedOverlay(Drawable defaultMarker, Activity activity)
	{
		super(defaultMarker, activity);
	}
	
	public MyLocationItemizedOverlay(Activity activity)
	{
		super(activity.getResources().getDrawable(R.drawable.androidmarkerred), activity);
	}
	
	
}
