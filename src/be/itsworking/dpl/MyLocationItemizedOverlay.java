/**
 * 
 */
package be.itsworking.dpl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.google.android.maps.OverlayItem;

/**
 * @author hackrylix
 *
 */
public class MyLocationItemizedOverlay extends MyItemizedOverlay
{

	/**
	 * @param defaultMarker
	 * @param context
	 */
	public MyLocationItemizedOverlay(Drawable defaultMarker, Activity context)
	{
		super(defaultMarker, context);
	}
	
	public MyLocationItemizedOverlay(Activity context)
	{
		super(context.getResources().getDrawable(R.drawable.androidmarkerred), context);
	}
	
	
}
