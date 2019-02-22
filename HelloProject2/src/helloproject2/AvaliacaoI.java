import java.util.List;
import java.util.ArrayList;
import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import helloproject2.ParseFeed;
import processing.core.PApplet;
import static processing.core.PConstants.JAVA2D;
import processing.core.PFont;

public class AvaliacaoI extends PApplet {
   
        
	
	private UnfoldingMap map;
        private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom";// Provedor que irá analizar no mapa a propriedade de cada um dos Terremotos
        private PFont font = loadFont("C:\\Users\\Artur Pereira\\Documents\\NetBeansProjects\\HelloProject2\\src\\helloproject2\\OpenSans-12.vlw"); // Fonte para mostrar no mapa o nome da cidade marcada
	public void settings() {
		//size(800, 600, JAVA2D);
	}
	

	public static void main(String[] args) {
		PApplet.main(new String[] { AvaliacaoI.class.getName() });
	}
	
	public void setup() {
		//map = new UnfoldingMap(this, new Microsoft.RoadProvider());
		size(1000, 780, JAVA2D);
		
		map = new UnfoldingMap(this,0,0,1020,840, new Microsoft.RoadProvider());//Provedor que irá fornecer o mapa
		map.zoomToLevel(2);// zoom do mapa
		//map.panTo(new Location(40f, -42f));
		MapUtils.createDefaultEventDispatcher(this, map);

		List<Marker> markers = createMarkers();
		map.addMarkers(markers);
		
		
		}
	
	public void draw() {
		background(240);
		map.draw();
                legenda();// legenda que aparecerá na tela para ajudar a identificar a função de cada ponto marcado
	}
	public List<Marker> createMarkers(){
	List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		List<Marker> markers = new ArrayList<Marker>();
		for (PointFeature feature : earthquakes) {
			
			
			String label = feature.getStringProperty("title");
                        Float magnitude = (Float) feature.getProperty("magnitude");//Pegar a magnitude para fazer as comparações  na hora da marcação
			PointFeature pointFeature = (PointFeature) feature;
			Marker marker = new LabeledMarker(pointFeature.getLocation(), label , font,10);
            
                        customizeMarker(marker,magnitude);
			markers.add(marker);
		
              
                }return markers;
	}  
       public void customizeMarker(Marker marker, Float magnitude) {
		//TODO: modifique a cor e tamanho dos marcadores conforme a magnitude  
               //Cores que serão usadas distinguir cada nivel de terremoto 
               
                if(magnitude < 4){
                   marker.setColor(color(0,0,255)); 
                   marker.setStrokeColor(color(0, 0, 255));
                   marker.setStrokeWeight(2);
                }
                  if(magnitude > 4 && magnitude < 4.9){
                     marker.setColor(color(255,204,0));
                     marker.setStrokeColor(color(255,204,0));
                     marker.setStrokeWeight(4);
                 }
                 
                 if(magnitude >= 5 ){
                     marker.setColor(color(255,0,0));
                     marker.setStrokeColor(color(255,0,0));
                     marker.setStrokeWeight(7);
                     }
               
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

private void legenda() { 
        //Lengenda que irá aparecer no mapa para destingui o nivel de cada tremor
        fill(color(255,255,240));
        rect(35,50,150,200);

        fill(color(0,0,0));
        textSize(12);
        text("Legendas de Niveis de  Terremotos", 50, 50, 150, 200 );

        fill(color(255,0,0));
        ellipse(40,100,10,10);
        fill(color(0,0,0));
        textSize(12);
        text("5+ Magnitude",60,105);

        fill(color(255,204,0));
        ellipse(40,140,8,8);
        fill(color(0,0,0));
        textSize(12);
        text("4+ Magnitude",60,145);

        fill(color(0,0,255));
        ellipse(40,190,5,5);
        fill(color(0,0,0));
        textSize(12);
        text("Abaixo de 4",60,195);

 
 }
}