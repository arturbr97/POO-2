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
public class MyPApplet extends PApplet{
    private final String url = "http://www.onordeste.com/administrador/personalidades/imagemPersonalidade/cd1fa87a9839e88ade3b92e616bba361276.jpg?x85514";
    private PImage backgroundImg;
    
    public static void main(String[]args) {
		
		PApplet.main("helloproject2.MyPApplet");
        }
    public void settings(){
        size(569,391);
    }
    public void setup(){
        backgroundImg = loadImage(url, "jpg");
        fill(255,255,0);
    }
    public void draw(){
     image(backgroundImg, 0,0);   
     ellipse(50,50,width/6,height/5);
    }
    
}
