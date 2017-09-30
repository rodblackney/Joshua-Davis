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

public class sketch_08_09 extends PApplet {







HDrawablePool pool;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	pool = new HDrawablePool(90);
	pool.autoAddToStage()
	.add(
		new HRect(6)
		.rounding(2)
		.anchorAt(H.CENTER)
		.noStroke()
	)

	.colorist( new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600).fillOnly() )

	.layout(
		new HGridLayout()
		.startLoc(9, height/2)
		.spacing(7, 0)
		.cols(90)
		)

	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HDrawable d = (HDrawable) obj;

				new HOscillator()
					.target(d)
					.property(H.HEIGHT)
					.range(6, 200)
					.speed(1)
					.freq(3)
					.waveform(H.TRIANGLE)
					.currentStep( pool.currentIndex()*3 ) 
				;
			}
		}
	)

	.requestAll()
;
}

public void draw() {

H.drawStage();

noFill(); strokeWeight(2); stroke(0xffCCCCCC);
// line(0, height/2, width, height/2);

	
}






  public void settings() { 	size(600, 500); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_09" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
