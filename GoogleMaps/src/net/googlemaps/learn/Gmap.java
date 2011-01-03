package net.googlemaps.learn;

import java.util.List;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;


public class Gmap extends MapActivity implements LocationListener{
	
	RelativeLayout zoomLayout;
	MapView mapView;
	MapController mapController;
	double latitude = 32.859979,longitude = -96.790237;
	GeoPoint currentPoint;
	int defaultZoomLevel = 12;
	List<Overlay> mapOverlays;
	Drawable drawable;
	OverlayMarker itemizedOverlay;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        
        zoomLayout = new RelativeLayout(this);
        setContentView(zoomLayout);
        
        mapView = new MapView(this, "0WZ0hImVuoBAUv0Whn3sO-qzFIwMh68Q8DalcGw");
        RelativeLayout.LayoutParams mapViewLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.FILL_PARENT,RelativeLayout.LayoutParams.FILL_PARENT );
        zoomLayout.addView(mapView, mapViewLayoutParams);
        
        RelativeLayout.LayoutParams zoomControlsLayoutParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT );
        zoomControlsLayoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        zoomControlsLayoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);

        zoomLayout.addView(mapView.getZoomControls(),zoomControlsLayoutParams);

        mapView.setClickable(true);
        mapView.setEnabled(true);
        
        mapController = mapView.getController();
        currentPoint = new GeoPoint((int) (latitude * 1000000), (int) (longitude * 1000000));
        mapController.setZoom(defaultZoomLevel);
        mapController.animateTo(currentPoint);
        mapView.invalidate();
        
        showCurrentLocation();
        
        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000L, 500.0f, this);
        
        mapOverlays = mapView.getOverlays();
        drawable = this.getResources().getDrawable(R.drawable.marker);
        itemizedOverlay = new OverlayMarker(drawable);
        
       currentPoint = new GeoPoint((int) latitude * 1000000, (int) longitude * 1000000);
        OverlayItem overlayitem = new OverlayItem(currentPoint, "Current Location", latitude + "  " + longitude);
        itemizedOverlay.addOverlay(overlayitem);
        mapOverlays.add(itemizedOverlay);
        
    }
    
    public void showCurrentLocation(){
    	
    	String currentLocation = "Latitude = " + latitude + ",  Longitude = " +longitude;
        Toast.makeText( getApplicationContext(),currentLocation,Toast.LENGTH_LONG).show();
        
    }
 
    public final Button.OnClickListener backToForm = new Button.OnClickListener() {
		public void onClick(View v) {
			
			//Intent myIntent = new Intent(v.getContext(), StaffMenu.class);
			//startActivityForResult(myIntent, 0);
		}
	};
    
	@Override
    protected boolean isRouteDisplayed() {
        // TODO Auto-generated method stub
        return false;
    }

	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		
		if (location != null) {
			latitude = location.getLatitude();
			longitude = location.getLongitude();
			currentPoint = new GeoPoint((int) latitude * 1000000, (int) longitude * 1000000);
			mapController.animateTo(currentPoint);
			showCurrentLocation();
			mapOverlays.clear();
			OverlayItem overlayitem = new OverlayItem(currentPoint, "Current Location", latitude + "  " + longitude);
			itemizedOverlay = null;
			itemizedOverlay = new OverlayMarker(drawable);
	        itemizedOverlay.addOverlay(overlayitem);
	        mapOverlays.add(itemizedOverlay);
	        mapView.invalidate();
		}
	}

	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}