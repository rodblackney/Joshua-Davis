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

public class sketch_5_06 extends PApplet {





// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times


HCanvas c1;
HColorPool colors;
HDrawablePool pool;
HTimer timer;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false).fade(5);
	H.add(c1);

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xff333333, 0xff0095A8, 0xffFF3300, 0xffFF6600);

	pool = new HDrawablePool(100);
	pool.autoParent(c1)
		.add (
			new HRect()
			.rounding(40)
			)

// what happens to each HRect from Pool
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.noStroke()
						.fill(colors.getColor(), 200)
						.loc( (int)random(width), (int)random(height) )
						.size( 25 + ((int)random(4) * 25) )
						.anchor(0, -50 )
					;

					new HRotate(d, random(-6, 6)) ;
				}
			}
		)
	;



	timer = new HTimer()
		.numCycles( pool.numActive() ) // how many cycles
		.interval(120) // between each time in milliseconds
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
    String[] appletArgs = new String[] { "sketch_5_06" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
