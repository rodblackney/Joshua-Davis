import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 
import hype.interfaces.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class HRotate_01 extends PApplet {


// HRotate







HRect r1;


public void setup() {

	
	H.init(this).background(0xff202020);
	

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc(width/2, height/2)
		.noStroke()
		.fill(0xffFF3300)
		;

	HRotate hr1 = new HRotate();
	hr1.target(r1).speed(6);
}

public void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
	
}
  public void settings() { 	size(600, 500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HRotate_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
