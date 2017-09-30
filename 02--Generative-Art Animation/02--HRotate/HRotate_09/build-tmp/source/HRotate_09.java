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

public class HRotate_09 extends PApplet {








HRect r1, r2, r3;

public void setup() {

	
	H.init(this).background(0xff202020).autoClear(false);
	

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchor(50, 150)
		.loc(width/2, height/2)
		.stroke(0xffFF3300)
		.fill(0xff202020)
	;

	r2 = new HRect(100).rounding(10);
	H.add(r2)
		.anchor(50, 200)
		.loc(width/2, height/2)
		.noStroke()
		.stroke(0xffFF3300)
		.fill(0xff202020)
	;

	r3 = new HRect(100).rounding(10);
	H.add(r3) 
		.anchor(50, 250)
		.loc(width/2, height/2)
		.noStroke()
		.stroke(0xffFF3300)
		.fill(0xff202020)
	;

	new HRotate(r1, 1);
	new HRotate(r2, 2);
	new HRotate(r3, -3);

	new HOscillator().target(r1).property(H.SCALE).range(0, 1).speed(0.5f).freq(5);
	new HOscillator().target(r2).property(H.SCALE).range(-1, 1).speed(0.5f).freq(5);
	new HOscillator().target(r3).property(H.SCALE).range(0, 2).speed(0.5f).freq(5);

}

public void draw() {
	H.drawStage();

//	noFill(); strokeWeight(2); stroke(#0095A8);
//	ellipse(r1.x(), r1.y(), 4, 4);
}




  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HRotate_09" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
