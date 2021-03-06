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

public class HSwarm_02 extends PApplet {





//import hype.extended.layout.*;
//import hype.interfaces.*;

HSwarm swarm;
HDrawablePool pool;
HTimer timer;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	swarm = new HSwarm()
		.addGoal(width/2, height/2)
		.speed(4)
		.turnEase(0.025f) // uses float to set degrees 
		.twitch(0)
	;

	pool = new HDrawablePool(40); // draw 40 assets
	pool.autoAddToStage() // add to stage
		.add (
			new HRect()
			.rounding(4)
			.size(18, 6)
			)

		.colorist(new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095a8, 0xff00616f, 0xffFF3300, 0xffFF6600).fillOnly())

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					float rotation = random(TWO_PI);
					HDrawable d = (HDrawable) obj;
					d
						.noStroke()
						.loc( width/2, 100) // assets start points
						.rotationRad(rotation)
						.move( cos(rotation)*10, sin(rotation)*10 )
						.anchorAt(H.CENTER)
						;
						swarm.addTarget(d);
				}
			}
		)
	;
		
	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(180)
		.callback(
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

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(width/2, 100, 4, 4); // start point
	stroke(0xffFF3300);
	ellipse(width/2, height/2, 4, 4); // end point
}





  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HSwarm_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
