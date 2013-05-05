/**
 * 
 */
package be.itsworking.dpl;

import java.net.URI;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import be.itsworking.dpl.activities.MyPharmacyActivity;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;

/**
 * @author hackrylix
 * 
 */
public class MyPharmacyItemizedOverlay extends MyItemizedOverlay
{

	/**
	 * @param defaultMarker
	 * @param context
	 */
	public MyPharmacyItemizedOverlay(Drawable defaultMarker, Activity context)
	{
		super(defaultMarker, context);
		// TODO Auto-generated constructor stub

	}

	public MyPharmacyItemizedOverlay(Activity context)
	{
		super(context.getResources().getDrawable(R.drawable.caducee), context);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean onTap(int index)
	{
		OverlayItem item = mOverlays.get(index);

		String[] arr = item.getSnippet().split(";");

		Intent mi = new Intent(mContext, MyPharmacyActivity.class);
		mi.putExtra("PHARMACY_ID", arr[0]);

		mContext.startActivityForResult(mi, 1);
		return true;
	}

}
