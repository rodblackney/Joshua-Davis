import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_5_14 extends PApplet {


// import hype.extended.behavior.*;

// Sphere

HSphere d;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

// draw 100 HRect to screen
for (int i = 0; i < 100; ++i) {
	d = new HSphere();
	d
		.size( (int)random(40,100) )
		.strokeWeight(1)
		.stroke(0xffFF3300)
		.fill(0xff111111)

		// add random axis rotation
		.rotationX( (int)random(360) )
		.rotationY( (int)random(360) )
		.rotationZ( (int)random(360) )		

		.loc( (int)random(width), (int)random(height), (int)random(-100,100) )
		// centers at middle of HRect	
		.anchorAt(H.CENTER);
		H.add(d);
	}
	H.drawStage();
}

  public void settings() { 	size(600, 600, P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_14" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
