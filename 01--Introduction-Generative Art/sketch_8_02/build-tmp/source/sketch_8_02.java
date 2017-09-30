import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 
import hype.interfaces.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_8_02 extends PApplet {








HDrawablePool pool;
HColorPool colors;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095a8, 0xff00616f, 0xffFF3300, 0xffFF6600);

	// draw 100 assets
	pool = new HDrawablePool(2000);
	pool.autoAddToStage()
	// one in six chance of being drawn
//	.add( new HShape("svg1.svg"))
//	.add( new HShape("svg2.svg"))
//	.add( new HShape("svg3.svg"))
//	.add( new HShape("svg4.svg"))
	.add( new HShape("svg5.svg"))
//	.add( new HShape("svg6.svg"))

	.layout (
		new HShapeLayout()
		.target(
			new HImage("shapeMap.png")
		)
	)						

	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HShape d = (HShape) obj;
				d

					.enableStyle(false)
					.noStroke()
//					.strokeJoin(ROUND)
//					.strokeCap(ROUND)
//					.strokeWeight(1)
//					.stroke(#000000)
					.anchorAt(H.CENTER)
					// shrink svg size
					.size( (int)random(10,30) )
					.rotate( (int)random(360) )
					;

					d.randomColors(colors.fillOnly());
				}			
		}
	)
	.requestAll()
	;

	H.drawStage();
}
  public void settings() { 	size(600, 600); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_8_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
