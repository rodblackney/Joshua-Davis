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

public class sketch_5_17 extends PApplet {


// import processing.pdf.*;

HShape d;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	for (int i = 0; i < 100; ++i) {
		d = new HShape("vectors.svg");
		d

		.enableStyle(false)
		.strokeWeight(1)
		.stroke(0xffFF3300)
		.fill(0xff111111)
		.size( (int)random(25,100) )
		.rotate( (int)random(360) )
		.loc( (int)random(width), (int)random(height) )
		.anchorAt(H.CENTER)
		;
		H.add(d);
	}

	H.drawStage();

}

  public void settings() { 	size(600, 600); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_17" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
