/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloproject2;
import processing.core.PApplet;
import processing.core.PImage;

/**
 *
 * @author Artur Pereira
 */
public class MyDinamicPApplet extends PApplet{
    private final String url = "http://www.onordeste.com/administrador/personalidades/imagemPersonalidade/cd1fa87a9839e88ade3b92e616bba361276.jpg?x85514";
    private PImage backgroundImg;
    
    public static void main(String[]args) {
		
		PApplet.main("helloproject2.MyDinamicPApplet");
        }
    public void settings(){
        size(569,412);
    }
    public void setup(){
        backgroundImg = loadImage(url, "jpg");
        backgroundImg.resize(0,height);
        image(backgroundImg,0,0);
        //fill(255,255,0);
    }
    public void draw(){
     int[] color = sunColorSec(second());
     fill (color[0],color[1],color[2]);
      ellipse(width/8,height/6,width/5,width/5);
     
             }
    public int[] sunColorSec(float seconds){
        int[] rgb = new int[3];
        float diff30 = Math.abs(30 - seconds);
        float ratio = diff30/30;
        rgb[0]=(int)(255*ratio);
        rgb[1]=(int)(255*ratio);
        rgb[2]=0;
        
        return rgb;
        
    }
    
}
