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

public class sketch_08_17 extends PApplet {







HCanvas c1;
HColorPool colors;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false);
	H.add(c1);

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600);

	// attach HPath
	for (int i=0; i<100; ++i) {

		HPath d = new HPath();
		d
			.polygon(8)
			.size(130, 10)
			// get colors for stroke using random seed
			.stroke( colors.getColor( i*250) )
			.fill(0xff000000)
			.anchorAt(H.CENTER)
			.loc(width/2, height/2)
		;

		c1.add(d);

		// Rotation
		new HOscillator()
			.target(d)
			.property(H.ROTATION)
			.range(-180, 180)
			.speed(1)
			.freq(0.5f)
			.currentStep(i)
			;

		// Scale	
		new HOscillator()
			.target(d)
			.property(H.SCALE)
			.range(0.25f, 1)
			.speed(2)
			.freq(4)
			.currentStep(i)
			;	

		// Y position	
		new HOscillator()
			.target(d)
			.property(H.Y)
			// from original y location
			.relativeVal( d.y() )
			.range(-500, 500)
			.speed(1)
			.freq(0.7f)
			.currentStep(i)
			;	

		// X position	
		new HOscillator()
			.target(d)
			.property(H.X)
			// from original x location
			.relativeVal( d.x() )
			.range(-500, 500)
			.speed(2)
			.freq(0.5f)
			.currentStep(i)
			;	

		}
	}



public void draw() {

H.drawStage();

	
}






  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
