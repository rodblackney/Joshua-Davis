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

public class HSwarm_07 extends PApplet {





//import hype.extended.layout.*;
//import hype.interfaces.*;

HCanvas c1;
HPixelColorist colors;
HSwarm swarm;
HDrawablePool pool;
HTimer timer;

public void setup() {
	
	H.init(this).background(0xff000000);
	

	c1 = new HCanvas().autoClear(false).fade(2);
	H.add(c1);

	colors = new HPixelColorist("color.jpg").fillOnly()
//		.fillOnly()
		.strokeOnly()
//		.fillAndStroke()
	;

	swarm = new HSwarm()
		.addGoal(H.mouse())
		.speed(5)
		.turnEase(0.05f) // uses float to set degrees 
		.twitch(20)
	;

	pool = new HDrawablePool(30); // number of assets to draw
	pool.autoParent(c1)
		.add (
			new HRect()
			.rounding(2)
			.size(18, 6)
		)

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.size( (int)random(12,6), (int)random(8, 8) )
						.noStroke()
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
    String[] appletArgs = new String[] { "HSwarm_07" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
