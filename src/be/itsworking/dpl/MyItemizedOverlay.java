package be.itsworking.dpl;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem>
{
	protected ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	protected Activity mContext;
	protected Drawable marker;

	public MyItemizedOverlay(Drawable defaultMarker, Activity context)
	{
		super(defaultMarker);
		mContext = context;
		marker=defaultMarker;
	}

	@Override
	protected boolean onTap(int index)
	{
		OverlayItem item = mOverlays.get(index);
		Util.showMessage(mContext,item.getTitle()+"\n"+item.getSnippet());
		return true;
	}
	

	public void addOverlay(OverlayItem overlay)
	{
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		return mOverlays.get(i);
	}
	
	public Drawable getMarker()
	{
		return marker;
	}

	@Override
	public int size()
	{
		return mOverlays.size();

	}

}
