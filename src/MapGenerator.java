import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.GoogleMap;
import com.lynden.gmapsfx.javascript.object.LatLong;
import com.lynden.gmapsfx.javascript.object.MapOptions;
import com.lynden.gmapsfx.javascript.object.Marker;
import com.lynden.gmapsfx.javascript.object.MarkerOptions;
import com.lynden.gmapsfx.shapes.Circle;
import com.lynden.gmapsfx.shapes.CircleOptions;

/**
 * This class calls the Google map API with the help of GMapsFX </p>
 * creates a {@link GoogleMapView} object which can be added to a JavaFX format as a node. </br>
 * creates a related {@link GoogleMap} object as the connection between java and javascript
 * @author CJC
 *
 */
public class MapGenerator implements MapComponentInitializedListener{

	GoogleMapView mapView;
	GoogleMap map;
	
	Marker marker;
	Circle circle;
	
	public MapGenerator() {
		mapView = new GoogleMapView();
		mapView.addMapInializedListener(this);
	}
	
	/**
	 * accessor for the Google map view
	 * @return the mapView which is considered as a node in JavaFX
	 */
	public GoogleMapView getMapView() {
		return mapView;
	}
	
	/**
	 * 
	 * @return the coordinate of the center of the map view
	 */
	public LatLong getCenter() {
		return map.getCenter();
	}
	
	/**
	 * Set the center of the map view
	 * @param loc the point on which this object sets the map view
	 */
	public void setCenter(LatLong loc) {
		map.setCenter(loc);
	}
	
	/**
	 * 
	 * @param scale the level to which this object zooms
	 */
	public void setZoom(int scale) {
		map.setZoom(scale);
	}
	
	/**
	 * move the marker to the location passed in.
	 * @param loc the point on which the marker is set
	 */
	public void setMarker(LatLong loc) {
		
		marker.setPosition(loc);
		marker.setVisible(true);

	}
	
	/**
	 * move the center of the circle to the location passed in
	 * @param loc the center of the circle
	 */
	public void setCircle(LatLong loc) {
		
		circle.setCenter(loc);
		circle.setVisible(true);

	}
	
	@Override
	public void mapInitialized() {
		// TODO Auto-generated method stub
		MapOptions mapOptions = new MapOptions();

		mapOptions
		.center(new LatLong(47.6097, -122.3331))
		.overviewMapControl(false)
		.panControl(false)
		.rotateControl(false)
		.scaleControl(false)
		.streetViewControl(false)
		.zoomControl(false)
		.mapTypeControl(false)
		.zoom(5);

		map = mapView.createMap(mapOptions);
		
		MarkerOptions markerOptions = new MarkerOptions();
		markerOptions
		.visible(false)
		.title("Location");
		marker = new Marker( markerOptions );
		map.addMarker(marker);
		
		CircleOptions circleOptions = new CircleOptions();
		circleOptions
		.fillColor("gray")
		.fillOpacity(0.20)
		.visible(false)
		.strokeColor("gray")
		.radius(241401.6); //radius, 150 MILE in meter
		
		circle = new Circle(circleOptions);
		map.addMapShape(circle);
	
	}

}
