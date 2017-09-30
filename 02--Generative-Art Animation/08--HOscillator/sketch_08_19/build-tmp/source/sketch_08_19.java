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

public class sketch_08_19 extends PApplet {





// import hype.interfaces.*;

HDrawablePool pool;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

	pool = new HDrawablePool(49);
	pool.autoAddToStage()
		.add (
			new HPath()

		)

	.colorist(new HColorPool( 0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600).fillOnly() )

	.layout (
		new HGridLayout()
		.startX(81)
		.startY(81)
		.spacing(80, 80)
		.cols(7)
		)

	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				int ranEdges = round( random(5, 10) );
				float ranDepth = random (0.25f, 0.75f);

				HPath path = (HPath) obj;
				path
					.star( ranEdges, ranDepth )
					.size(64)
					.noStroke()
					.anchorAt(H.CENTER)
					.rotation( (int)random(360) )

					;

					new HOscillator()
						.target(path)
						.property(H.ROTATIONY)
						.range(-180, 180)
						.speed(0.5f)
						.freq(5)
						.currentStep(pool.currentIndex())
					;

					new HOscillator()
						.target(path)
						.property(H.ROTATIONZ)
						.range(-180, 180)
						.speed(0.3f)
						.freq(5)
						.currentStep(pool.currentIndex())
					;


					new HOscillator()
						.target(path)
						.property(H.Z)
						.range(-50, 50)
						.speed(2)
						.freq(1)
						.currentStep(pool.currentIndex()*8)
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
    String[] appletArgs = new String[] { "sketch_08_19" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
