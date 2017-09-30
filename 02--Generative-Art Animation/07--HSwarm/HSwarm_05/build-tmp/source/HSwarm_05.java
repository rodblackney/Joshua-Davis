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

public class HSwarm_05 extends PApplet {





//import hype.extended.layout.*;
//import hype.interfaces.*;

HColorField colors;
HSwarm swarm;
HDrawablePool pool;
HTimer timer;

public void setup() {
	
	H.init(this).background(0xff3300FF).autoClear(false);
	

	colors = new HColorField(width, height)
		.addPoint(0, height/2, 0xffFF0066, 0.5f)
		.addPoint(width, height/2, 0xff3300FF, 0.5f)
		.fillOnly()
//		.strokeOnly()
//		.fillAndStroke()
	;

	swarm = new HSwarm()
		.addGoal(H.mouse())
		.speed(5)
		.turnEase(0.05f) // uses float to set degrees 
		.twitch(20)
	;

	pool = new HDrawablePool(40); // draw 40 assets
	pool.autoAddToStage() // add to stage
		.add (
			new HRect()
			.rounding(4)
			.size(18, 6)
		)

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.size( (int)random(10,20), (int)random(2,6) )
						.stroke(0xff202020, 100)
						.fill(0xff000000)
						.loc( width/2, height/2) // assets start points
						.anchorAt(H.CENTER)
					;
						swarm.addTarget(d);
				}
			}
		)
	;
		
	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(250)
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

	for(HDrawable d : pool) {
		colors.applyColor(d.fill(0xff000000));
	}


	H.drawStage();

}





  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HSwarm_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
