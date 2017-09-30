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

public class sketch_08_18 extends PApplet {







HCanvas c1;
HColorPool colors;
HRect d;


public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false).fade(2);
	H.add(c1);

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600);

	// attach HRects
	for (int i=0; i<200; ++i) {

		d = new HRect().rounding(20);
		d
			.noStroke()
			.fill( colors.getColor(i * 250) )
			.loc(width/2, height/2)
			.anchor(50, -50)
		;
		c1.add(d);

		// Rotation
		new HOscillator()
			.target(d)
			.property(H.ROTATION)
			.range(-180, 180)
			.speed(0.1f)
			.freq(4)
			.currentStep(i)
			;

		// Scale	
		new HOscillator()
			.target(d)
			.property(H.SCALE)
			.range(0.25f, 1)
			.speed(1)
			.freq(4)
			.currentStep(i*3)
			;	

		// y position	
		new HOscillator()
			.target(d)
			.property(H.Y)
			// from original y location
			.relativeVal( d.y() )
			.range(-300, 300)
			.speed(0.5f)
			.freq(2)
			.currentStep(i)
			;	

		// x position	
		new HOscillator()
			.target(d)
			.property(H.X)
			// from original x location
			.relativeVal( d.x() )
			.range(-300, 300)
			.speed(0.5f)
			.freq(1)
			.currentStep(i)
			;	

		}
	}



public void draw() {

H.drawStage();

	
}






  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_18" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
