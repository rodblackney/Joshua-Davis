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

public class sketch_08_23 extends PApplet {







HColorPool colors;
HDrawablePool pool;

float ringScale = 200;
int ringSteps = 5;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

	colors = new HColorPool(0xffFFFFFF, 0xffF7F7F7, 0xffFF3300, 0xff242424, 0xff333333, 0xff666666);

	pool = new HDrawablePool(150);
	pool.autoAddToStage()
		.add(
			new HShape("ring.svg")
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
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.fill( colors.getColor(i*250) )
						.size( ringScale )
						.anchorAt(H.CENTER)
						.z(0)
				;

				ringScale = ringScale - ringSteps;

				// x axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONX)
					.range(-360, 360)
					.speed(0.1f)
					.freq(1)
					.currentStep(i)	
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
					.range(0.5f, 1.0f)
					.speed(0.3f)
					.freq(5)
					.currentStep(i)
				;

			}
		}
	)

		.requestAll()
	;
}



public void draw() {


H.drawStage();

	
}






  public void settings() { 	size(640,640,P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_23" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
