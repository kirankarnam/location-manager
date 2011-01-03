package net.googlemaps.learn;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class OverlayMarker extends ItemizedOverlay {

	private ArrayList<OverlayItem> overlayItems = new ArrayList<OverlayItem>();
	Context mContext;
	
	public OverlayMarker(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
		// TODO Auto-generated constructor stub
	}

	@Override
	protected OverlayItem createItem(int i) {
		// TODO Auto-generated method stub
		return overlayItems.get(i);
	}

	public void addOverlay(OverlayItem overlay) {
	    overlayItems.add(overlay);
	    populate();
	}
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return overlayItems.size();
	}

	public OverlayMarker(Drawable defaultMarker, Context context) {
		  super(defaultMarker);
		  mContext = context;
		}
	
}
