import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_5_08 extends PApplet {





// import hype.extended.layout.*;
// import hype.interfaces.*;


HRandomTrigger randomTrigger;
HRect r1;


public void setup() {
	
	H.init(this).background(0xff202020);
	

	/*
	* Create a new randomTrigger with a 1 in 15 chance
	* of triggering everytime H.drawStage() is called
	*/

randomTrigger = new HRandomTrigger(1f/40);
// 1f/15 chance 1 in 15 chance

r1 = new HRect(100).rounding(10);
H.add(r1)
	.loc( (int)random(width),(int)random(height))
	.noStroke()
	.fill(0xffFF3300)
	.rotation(45)
	;

randomTrigger.callback(
	new HCallback() {
		public void run(Object obj) {
			r1.loc ( (int)random(width), (int)random(height) );
			}
		}

		)
	;
}




public void draw() {
	H.drawStage();
}

  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_08" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
