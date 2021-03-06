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

public class HRotate_07 extends PApplet {


// HRotate







HRect r1, r2, r3;


public void setup() {

	
	H.init(this).background(0xff202020);
	

	// rectangle #1
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchor(50, 25) // use hype constants
		.loc(width/2 -160, height/2)
		.noStroke()
		.fill(0xffFF3300)
		;

	// rectangle #2
	r2 = new HRect(100).rounding(10);
	H.add(r2)
		.anchor(50, -25)
		.loc(width/2, height/2)
		.noStroke()
		.fill(0xffFF6600)
		;

	// rectangle #3
	r3 = new HRect(100).rounding(10);
	H.add(r3)
		.anchor(0, 150)
		.loc(width/2 + 160, height/2)
		.noStroke()
		.fill(0xffFF9900)
		;			

		new HRotate(r1, 0.5f);
		new HRotate(r2, 1);
		new HRotate(r3, 1.5f);
}

public void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
	ellipse(r2.x(), r2.y(), 4, 4);
	ellipse(r3.x(), r3.y(), 4, 4);
	
}
  public void settings() { 	size(600, 500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HRotate_07" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
