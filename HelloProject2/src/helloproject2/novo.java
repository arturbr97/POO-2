/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloproject2;
import java.util.ArrayList;
import java.util.List;

import de.fhpotsdam.unfolding.UnfoldingMap;
import de.fhpotsdam.unfolding.data.PointFeature;
import de.fhpotsdam.unfolding.geo.Location;
import de.fhpotsdam.unfolding.marker.Marker;
import de.fhpotsdam.unfolding.providers.Microsoft;
import de.fhpotsdam.unfolding.utils.MapUtils;
import processing.core.PFont;

import processing.core.PApplet;

@SuppressWarnings("serial")
public class novo extends PApplet{
	
	//private static final long  serialVersionUID = 1L;
	
	private UnfoldingMap map;
    private String earthquakesURL = "https://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/4.5_week.atom";
    public static void main(String[] args) {
		PApplet.main(new String[] { novo.class.getName() });
	}
	
    
	public void setup() {
		size(960,610, JAVA2D);
		
		map = new UnfoldingMap(this,0,0,900,700, new Microsoft.RoadProvider());
		map.zoomToLevel(2);
		MapUtils.createDefaultEventDispatcher(this, map);
		
		List<Marker> markers = createMarkers();
		map.addMarkers(markers);

	}
	
	public void draw() {
		background(240);
		// Drawing Markers in handled internally
		map.draw();
		addKey();
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

	public List<Marker> createMarkers(){
		
		List<PointFeature> earthquakes = ParseFeed.parseEarthquake(this, earthquakesURL);
		
		Location ValdiviaLocation = new Location(-38.14f,-73.03f);
		PointFeature valEq = new PointFeature(ValdiviaLocation);
		valEq.addProperty("title", "Valdivia, Chile; \n Magnitude: 9.5; \n Date May 22 de 1960 \n");

		earthquakes.add(valEq);
		
		Location AlaskaLocation = new Location(60f,147f);
		PointFeature alaskaEq = new PointFeature(AlaskaLocation);
		alaskaEq.addProperty("title", "Prince William Sound, Alaska, United States; \n Magnitude: 9.2; \n Date March 27 de 1964 \n");

		earthquakes.add(alaskaEq);
		
		Location SumatraLocation = new Location(0f,102f);
		PointFeature sumatraEq = new PointFeature(SumatraLocation);
		sumatraEq.addProperty("title", "Sumatra, Indonesia; \n Magnitude: 9.1; \n Date December 26 de 2044 \n");

		earthquakes.add(sumatraEq);
		
		Location TohokuLocation = new Location(1f,105f);
		PointFeature tohokuEq = new PointFeature(TohokuLocation);
		tohokuEq.addProperty("title", "Tohoku Region Japan, Indonesia Oceano Pacífico ; \n Magnitude: 9.1; \n Date December 26 de 2044 \n");

		earthquakes.add(tohokuEq);
		
		Location KamchatkaLocation = new Location(7f,110f);
		PointFeature KamEq = new PointFeature(KamchatkaLocation);
		KamEq.addProperty("title","Kamchatka Ruusia SFSR, União Soviética, Indonesia; \n Magnitude: 9.0; \n Date Novembro 4 de 1952 \n");

		earthquakes.add(KamEq);
		
		PFont font = loadFont("C:\\Users\\Artur Pereira\\Documents\\NetBeansProjects\\HelloProject2\\src\\helloproject2\\OpenSans-12.vlw");
		List<Marker> markers = new ArrayList<Marker>();
		
		for (PointFeature feature : earthquakes) {
						
                        String label = feature.getStringProperty("title");
			PointFeature pointFeature = (PointFeature) feature;
			Marker marker = new LabeledMarker(pointFeature.getLocation(), label, font, 15);
			markers.add(marker);
		}
		return markers;
        }
	
	public void customizeMarker(Marker marker, Float magnitude) {
		//TODO: modifique a cor e tamanho dos marcadores conforme a magnitude  
                
	}
	
	public void addKey() {
		//TODO: esse mÃ©todo deve implementar a legenda 
		// e deverÃ¡ ser chamado dentro do mÃ©todo draw() 
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
