import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_08_21 extends PApplet {





// import hype.interfaces.*;

HDrawablePool pool;
int boxSize = 150;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add(
			new HBox()
		)

	.layout (
		new HGridLayout()
		.startX(-125)
		.startY(-125)
		.spacing(100, 100)
		.cols(10)
		)


	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				int i = pool.currentIndex();
					HBox d = (HBox) obj;
					d
						.depth(boxSize * 3)
						.width(boxSize * 8)
						.height(boxSize)
						.noStroke()
						.z(-500)
				;

				// x axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONX)
					.range(-180, 180)
					.speed(0.3f)
					.freq(5)
					.currentStep(i*2)	
				;

				// y axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONY)
					.range(-180, 180)
					.speed(0.3f)
					.freq(1)
					.currentStep(i*2)
				;

				// z axis
				new HOscillator()
					.target(d)
					.property(H.ROTATIONZ)
					.range(-360, 360)
					.speed(0.5f)
					.freq(1)
					.currentStep(i*2)
				;

			}
		}
	)

		.requestAll()
	;
}



public void draw() {
	pointLight(255, 51, 0, 0, height/2, -300);
	pointLight(0, 149, 168, width, height/2, -300);
	pointLight(255, 284, 0, width/2, height/2, -400);


H.drawStage();

	
}






  public void settings() { 	size(640,640,P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_21" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
