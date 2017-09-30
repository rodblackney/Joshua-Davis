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

public class sketch_5_05 extends PApplet {





// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HDrawablePool pool;
HTimer timer;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add (
			new HRect()
			.rounding(10)
			)

			.colorist(new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xff333333, 0xff0095A8, 0xffFF3300, 0xffFF6600).fillOnly())

// what happens to each HRect from Pool
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.strokeWeight(4)
						.stroke(0xff000000, 75)
						.loc( (int)random(width), (int)random(height) )
						.rotation(45)
						.size( 50+((int)random(3)*50) ) // 3 X 50 + 50
						.anchorAt( H.CENTER )
					;
				}
			}
		)
	;



	timer = new HTimer()
		.numCycles( pool.numActive() ) // how many cycles
		.interval(100) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					pool.request();
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
    String[] appletArgs = new String[] { "sketch_5_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
