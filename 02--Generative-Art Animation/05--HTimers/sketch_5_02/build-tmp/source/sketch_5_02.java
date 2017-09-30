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

public class sketch_5_02 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HTimer timer;
HRect r1;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc( (int)random(width), (int)random(height) )
		.noStroke()
		.fill(0xffFF3300)
		.rotation(45)
	;



	timer = new HTimer()
		.numCycles(50)
		.interval(250) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					r1.loc( (int)random(width), (int)random(height));
				}
			}
		)
	;
}

public void draw() {
	H.drawStage();
}

  public void settings() { 	size(640,640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
