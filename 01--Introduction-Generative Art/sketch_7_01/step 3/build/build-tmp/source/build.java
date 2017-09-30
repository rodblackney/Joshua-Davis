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

public class build extends PApplet {








HDrawablePool pool;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	// draw 100 assets
	pool = new HDrawablePool(100);
	pool.autoAddToStage()
	// what
	.add( new HRect(),10 )
	.add( new HRect().rounding(10) )	
	// 
	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HDrawable d = (HDrawable) obj;
				d
					.strokeWeight(1)
					.stroke(0xffFF3300)
					.fill(0xff111111)
					.size( (int)random(25,125) )
					.rotate( (int)random(360) )
					.loc( (int)random(width), (int)random(height) )
					.anchorAt(H.CENTER)
					;
				}			
		}
	)
	.requestAll()
	;

	H.drawStage();
}
  public void settings() { 	size(600, 600); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
