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

public class HRotate_08 extends PApplet {








HRect r1, r2;

public void setup() {

	
	H.init(this).background(0xff202020);
	

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchor(50, -50)
		.loc(width/2, height/2)
		.noStroke()
		.fill(0xffFF3300)
	;

	r2 = new HRect(50).rounding(10);
	r1.add(r2) // attach r2 to r1
		.anchor(25, -25)
		.noStroke()
		.fill(0xff00616F)
	;

	// nests smaller rect (r2) inside larger (r1)
	new HRotate(r1, 1);

}

public void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
}




  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HRotate_08" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
