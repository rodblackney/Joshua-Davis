import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_5_10 extends PApplet {




// Star

HPath d;

public void setup() {
	
	H.init(this).background(0xff202020);
	

// draw 100 HRect to screen
for (int i = 0; i < 100; ++i) {
	d = new HPath();
	d

//		.polygon(5)
		// hexagons, polygons, triangles
		.star( 5, 0.5f )
		.strokeWeight(1)
		.stroke(0xffFF3300)
		// changes alpha to 200
		.fill(0xff111111)
		// random size number with a range between 25 & 125
		.size( (int)random(75,125) )
		// rotates randomly
		.rotate( (int)random(360) )
		// chooses a random location		
		.loc( (int)random(width), (int)random(height) )
		// centers at middle of HRect	
		.anchorAt(H.CENTER);

		H.add(d);

	}

	H.drawStage();

}

  public void settings() { 	size(600, 600); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_10" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
