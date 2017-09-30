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

public class sketch_5_04 extends PApplet {





// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HCanvas c1;
HTimer timer;
HRect r1;
HColorPool colors;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false).fade(2);
	H.add(c1);

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xff333333, 0xff0095A8, 0xffFF3300, 0xffFF6600);

	r1 = new HRect(100).rounding(10);
	c1.add(r1)
		.anchorAt(H.CENTER)
		.loc( (int)random(width), (int)random(height) )
		.noStroke()
		.fill( colors.getColor() )
//		.rotation(45)
		.size( 25+((int)random(4)*25) )
	;

	timer = new HTimer()
//		.numCycles(20) // how many cycles
		.interval(1) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					r1
						.loc( (int)random(width), (int)random(height))
						.fill( colors.getColor() )
						.size( 25 + ((int)random(3)*25) ) // picks between 0, 25, 50 then adds 25, 50, 75
					;
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
    String[] appletArgs = new String[] { "sketch_5_04" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
