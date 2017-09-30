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

public class sketch_08_15 extends PApplet {








HColorPool colors;
HRect r1;

int startScale = 450;
int scaleOffset = 10;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600);

	for (int i=0; i<40; ++i) {

		r1 = new HRect().rounding(20);
		r1
			.size(startScale)
			.noStroke()
			.fill( colors.getColor() )
			.loc(width/2, height/2)
			.anchorAt(H.CENTER)
			.rotation(45)
		;
		H.add(r1);

		new HOscillator()
			.target(r1)
			.property(H.ROTATION)
			.relativeVal(45)
			.range(-45, 45)
			.speed(0.4f)
			.freq(10)
			.currentStep(i)
			;


		new HOscillator()
			.target(r1)
			.property(H.SCALE)
			.range(0.5f, 1.5f)
			.speed(0.2f)
			.freq(8)
			.currentStep(i * 2)
			;	

		new HOscillator()
			.target(r1)
			.property(H.Y)
			// from original y location
			.relativeVal( r1.y() )
			.range(-75, 75)
			.speed(0.5f)
			.freq(5)
			.currentStep(i)
			;	

		new HOscillator()
			.target(r1)
			.property(H.X)
			// from original x location
			.relativeVal( r1.x() )
			.range(-50, 50)
			.speed(0.5f)
			.freq(8)
			.currentStep(i * 2)
			;	

			
		// uses scaleOffset to -10 from startScale
		// 440, 430, 420, 410
		startScale -= scaleOffset;

		}
	}



public void draw() {

H.drawStage();

	
}






  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_15" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
