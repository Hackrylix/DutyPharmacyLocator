package be.itsworking.dpl.map;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import be.itsworking.dpl.tools.Util;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class MyItemizedOverlay extends ItemizedOverlay<OverlayItem>
{
	protected ArrayList<OverlayItem> olverlayList = new ArrayList<OverlayItem>();
	protected Activity activity;
	protected Drawable marker;

	public MyItemizedOverlay(Drawable defaultMarker, Activity activity)
	{
		super(defaultMarker);
		this.activity = activity;
		marker = defaultMarker;
	}

	@Override
	protected boolean onTap(int index)
	{
		OverlayItem item = olverlayList.get(index);
		Util.showMessage(activity, item.getTitle() + "\n" + item.getSnippet());
		return true;
	}

	public void addOverlay(OverlayItem overlay)
	{
		olverlayList.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int i)
	{
		return olverlayList.get(i);
	}

	public Drawable getMarker()
	{
		return marker;
	}

	@Override
	public int size()
	{
		return olverlayList.size();
	}

}
