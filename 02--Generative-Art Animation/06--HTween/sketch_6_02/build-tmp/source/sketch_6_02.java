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

public class sketch_6_02 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HRect r1;
HTween t1;

int startX = 160;
int endX = 480;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchorAt(H.CENTER)
		.loc(startX, height/2)
		.noStroke()
		.fill(0xffFF3300)
		;

	// tween from left to right	
	t1 = new HTween()
		.target(r1)
		.property(H.LOCATION)
		.start(startX, height/2)
		.end(endX, height/2)
		.ease(0.01f)
		.spring(0.95f)
	;	
}

public void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(startX, height/2, 4, 4);
	ellipse(endX, height/2, 4, 4);
}



  public void settings() { 	size(600, 500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_6_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
