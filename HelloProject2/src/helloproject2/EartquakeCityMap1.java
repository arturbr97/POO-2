
package helloproject2;

import processing.core.PApplet;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.providers.Google;
import de.fhpotsdam.unfolding.utils.MapUtils;
import de.fhpotsdam.unfolding.providers.Microsoft;

public class EartquakeCityMap1 extends PApplet {
  

   private UnfoldingMap map;
   public void settings(){
       size(800, 600);
      
   }
 
    public void setup() {
        
     //   map = new UnfoldingMap(this,200,50,700,500, new Google.GoogleMapProvider());
       // map.zoomLevel(1);
        // Show map around the location in the given zoom level.
    
        map = new UnfoldingMap(this, new Microsoft.AerialProvider());
        MapUtils.createDefaultEventDispatcher(this, map);
        map.zoomAndPanTo(new Location(-07.04f, -41.28f), 15);
    }
 
    public void draw() {
        background(10);
        map.draw();
    }
     public static void main(String[] args) {
    PApplet.main(new String[] { "helloproject2.EartquakeCityMap" });
    
   }
 
}

