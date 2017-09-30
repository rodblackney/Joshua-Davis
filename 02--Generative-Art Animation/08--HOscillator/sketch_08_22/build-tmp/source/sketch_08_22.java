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

public class sketch_08_22 extends PApplet {





// import hype.interfaces.*;

HDrawablePool pool;
int boxSize = 500;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add(
			new HBox()
		)

	.layout (
		new HGridLayout()
		.startX(width/2)
		.startY(height/2)
		.spacing(0, 0)
		.cols(10)
		)


	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				int i = pool.currentIndex();
					HBox d = (HBox) obj;
					d
						// .depth(boxSize * 3)
						.depth(boxSize)
						.width(boxSize)
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

				// scale
				new HOscillator()
					.target(d)
					.property(H.SCALE)
					.range(0.2f, 1.2f)
					.speed(0.3f)
					.freq(5)
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
	pointLight(0, 149, 168, width, height/2, -100);
	pointLight(255, 284, 0, width/2, 0, -50);


H.drawStage();

	
}






  public void settings() { 	size(640,640,P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_22" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
