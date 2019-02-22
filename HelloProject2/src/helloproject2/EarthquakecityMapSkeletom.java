import java.util.ArrayList;
//import java.util.Collections;
//import java.util.Comparator;
import java.util.List;

//Unfolding libraries
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.marker.SimplePointMarker;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.providers.MBTilesMapProvider;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.geo.Location;
//import helloproject2.AvaliacaoI;
import helloproject2.ParseFeed;
import helloproject2.novo;
//Processing library
import processing.core.PApplet;

/** EarthquakeCityMap
 * An application with an interactive map displaying earthquake data.
 * Author: UC San Diego Intermediate Software Development MOOC team
 * @author Your name here
 * Date: July 17, 2015
 * */
@SuppressWarnings("unused")
public class EarthquakecityMapSkeletom extends PApplet {
    public static void main(String[] args) {
		PApplet.main(new String[] { EarthquakecityMapSkeletom.class.getName() });
	}

 // You can ignore this.  It's to keep eclipse from generating a warning.
 private static final long serialVersionUID = 1L;

 // IF YOU ARE WORKING OFFLINE, change the value of this variable to true
 private static final boolean offline = false;
 
 // Less than this threshold is a light earthquake
 public static final float THRESHOLD_MODERATE = 5;
 // Less than this threshold is a minor earthquake
 public static final float THRESHOLD_LIGHT = 4;

 /** This is where to find the local tiles, for working without an Internet connection */
 public static String mbTilesString = "blankLight-1-3.mbtiles";
 
 // The map
 private UnfoldingMap map;
 
 //feed with magnitude 2.5+ Earthquakes
 private String earthquakesURL = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/2.5_week.atom";

 
 public void setup() {
 //Added by Neha
 //Location location = null;
 //Float mag;
 
 size(950, 600, OPENGL);


 map = new UnfoldingMap(this, 200, 50, 700, 500, new Google.GoogleMapProvider());
 // IF YOU WANT TO TEST WITH A LOCAL FILE, uncomment the next line
 //earthquakesURL = "2.5_week.atom";
 
 
     map.zoomToLevel(2);
     MapUtils.createDefaultEventDispatcher(this, map); 
 
     // The List you will populate with new SimplePointMarkers
     List<Marker> markers = new ArrayList<Marker>();

     //Use provided parser to collect properties for each earthquake
     //PointFeatures have a getLocation method
     
     List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
     
     // These print statements show you (1) all of the relevant properties 
     // in the features, and (2) how to get one property and use it
     if (earthquakes.size() > 0) {
      PointFeature f = earthquakes.get(0);
      
      System.out.println(f.getProperties());
      Object magObj = f.getProperty("magnitude");
      @SuppressWarnings("unused")
	Float mag = Float.parseFloat(magObj.toString());
      // PointFeatures also have a getLocation method
     }
     
     //Added by Neha
     for (PointFeature feat : earthquakes){
      SimplePointMarker eqMark = createMarker(feat);
      Object magObj = feat.getProperty("magnitude");
      Float mag = Float.parseFloat(magObj.toString());
      if(mag >= 5.0f){
      eqMark.setColor(color(255,0,0));
      eqMark.setRadius(10.0f);
      } else if(mag < 5.0f && mag >= 4.0f){
      eqMark.setColor(color(255,204,0));
      eqMark.setRadius(8.0f);
      }else{
      eqMark.setColor(color(0,0,255));
      eqMark.setRadius(5.0f);
      }
      markers.add(eqMark); 
     }
        map.addMarkers(markers);
     
     
     
     
     // Here is an example of how to use Processing's color method to generate 
     // an int that represents the color yellow.  
     @SuppressWarnings("unused")
	int yellow = color(255, 255, 0);
     
     //TODO: Add code here as appropriate
 }
 
 // A suggested helper method that takes in an earthquake feature and 
 // returns a SimplePointMarker for that earthquake
 // TODO: Implement this method and call it from setUp, if it helps
 private SimplePointMarker createMarker(PointFeature feature)
 {
 // finish implementing and use this method, if it helps.
 return new SimplePointMarker(feature.getLocation());
 }
 
 public void draw() {
     background(10);
     map.draw();
     addKey();
 }


 // helper method to draw key in GUI
 // TODO: Implement this method to draw the key
 private void addKey() 
 { 
 // Remember you can use Processing's graphics methods here
        fill(color(170,208,200));
        rect(30,40,150,200);

        fill(color(0,0,0));
        text("Earthquake Key", 50, 50, 150, 200 );

        fill(color(255,0,0));
        ellipse(40,100,10,10);
        fill(color(0,0,0));
        text("5+ magnitude",60,105);

        fill(color(255,204,0));
        ellipse(40,140,8,8);
        fill(color(0,0,0));
        text("4+ magnitude",60,145);

        fill(color(0,0,255));
        ellipse(40,190,5,5);
        fill(color(0,0,0));
        text("Below 4",60,195);

 
 }
}
