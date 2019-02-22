import java.util.List;
import java.util.ArrayList;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.data.Feature;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.Microsoft.HybridProvider;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import helloproject2.ParseFeed;
import processing.core.PApplet;
import processing.core.PFont;

public class EarthquakeCityMap extends PApplet {
	
	private UnfoldingMap map;
        private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom";
	public void settings() {
		//size(800, 600, JAVA2D);
	}
	

	public static void main(String[] args) {
		PApplet.main(new String[] { EarthquakeCityMap.class.getName() });
	}
	
	public void setup() {
		//map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		size(1100, 900, JAVA2D);
		
		map = new UnfoldingMap(this,0,0,1020,840, new Microsoft.HybridProvider());
		map.zoomToLevel(2);
		//map.panTo(new Location(40f, -42f));
		MapUtils.createDefaultEventDispatcher(this, map);

		List<Marker> markers = createMarkers();
		map.addMarkers(markers);
		for (Marker marker : markers) {
			marker.setColor(color(255,0,0,100));
			marker.setStrokeColor(color(233,0,0));
			marker.setStrokeWeight(2);
		}
		
		//SimplePointMarker valdiviaMarker = new SimplePointMarker(ValdiviaLocation);
		//Marker valdiviaMarker = new SimplePointMarker(ValdiviaLocation, valEq.getProperties());
		//map.addMarker(valdiviaMarker);

		//valdiviaMarker.setColor(color(255, 0, 0, 100));
		//valdiviaMarker.setStrokeColor(color(255, 0, 0));
		//valdiviaMarker.setStrokeWeight(2);

	}
	
	public void draw() {
		background(240);

		// Drawing Markers in handled internally
		map.draw();
	}
	public List<Marker> createMarkers(){
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		
	/*	Location ValdiviaLocation = new Location(-38.14f,-73.03f);
		PointFeature valEq = new PointFeature(ValdiviaLocation);
		valEq.addProperty("title", "Valdivia, Chile; \n Magnitude: 9.5; \n Date May 22 de 1960 \n");
//		valEq.addProperty("title", "Valdivia, Chile");
//		valEq.addProperty("magnitude", "9.5");
//		valEq.addProperty("date", "May 22");
//		valEq.addProperty("date", "1960");
//		bigEqs.add(valEq);
		earthquakes.add(valEq);
		
		Location AlaskaLocation = new Location(60f,147f);
		PointFeature alaskaEq = new PointFeature(AlaskaLocation);
		alaskaEq.addProperty("title", "Prince William Sound, Alaska, United States; \n Magnitude: 9.2; \n Date March 27 de 1964 \n");
//		valEq.addProperty("title", "Prince William Sound, Alaska, United States");
//		valEq.addProperty("magnitude", "9.2");
//		valEq.addProperty("date", "March 27");
//		valEq.addProperty("date", "1964");
//		bigEqs.add(alaskaEq);
		earthquakes.add(alaskaEq);
		
		Location SumatraLocation = new Location(0f,102f);
		PointFeature sumatraEq = new PointFeature(SumatraLocation);
		sumatraEq.addProperty("title", "Sumatra, Indonesia; \n Magnitude: 9.1; \n Date December 26 de 2044 \n");
//		valEq.addProperty("title", "Sumatra, Indonesia");
//		valEq.addProperty("magnitude", "9.1");
//		valEq.addProperty("date", "December 26");
//		valEq.addProperty("date", "2004");
//		bigEqs.add(sumatraEq);
		earthquakes.add(sumatraEq);
		*/
		PFont font = loadFont("C:\\Users\\Artur Pereira\\Documents\\NetBeansProjects\\HelloProject2\\src\\helloproject2\\OpenSans-12.vlw");
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature feature : earthquakes) {
			//markers.add(new SimplePointMarker(feature.getLocation(),feature.getProperties()));
			
			String label = feature.getStringProperty("title");
			PointFeature pointFeature = (PointFeature) feature;
			Marker marker = new LabeledMarker(pointFeature.getLocation(), label, font, 15);
			markers.add(marker);
		}
		return markers;
	}
	public void mouseMoved() {
        // Deselect all marker
		for (Marker marker : map.getMarkers()) {
			marker.setSelected(false);
		}

		// Select hit marker
		Marker marker = map.getFirstHitMarker(mouseX, mouseY);
		// NB: Use mm.getHitMarkers(x, y) for multi-selection.
		if (marker != null) {
			marker.setSelected(true);
		}
	}
}


	
	
       

