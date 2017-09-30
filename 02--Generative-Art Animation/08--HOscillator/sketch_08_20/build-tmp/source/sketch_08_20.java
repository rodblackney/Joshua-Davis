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

public class sketch_08_20 extends PApplet {





// import hype.interfaces.*;

HDrawablePool pool;
HColorPool colors;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	colors = new HColorPool( 0xffFFFFFF, 0xffF7F7F7, 0xffECECEC, 0xff333333, 0xff0095A8, 0xff00616f, 0xffFF3300, 0xffFF6600);

	pool = new HDrawablePool(144);
	pool.autoAddToStage()

		.add(new HShape("svg1.svg"))
		.add(new HShape("svg2.svg"))
		.add(new HShape("svg3.svg"))
		.add(new HShape("svg4.svg"))
		.add(new HShape("svg5.svg"))
		.add(new HShape("svg6.svg"))


	.layout (
		new HGridLayout()
		.startX(50)
		.startY(50)
		.spacing(50, 50)
		.cols(12)
		)


	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				HShape d = (HShape) obj;
				d
					.enableStyle(false)
					.noStroke()
					.rotate( (int)random(4) * 90 )
					.size( 50 + ( (int)random(4) * 50 ) ) // 50, 100, 150, 200
				;

				d.randomColors(colors.fillOnly());

				new HOscillator()
					.target(d)
					.property(H.ROTATION)
					.range(-180, 180)
					.speed(0.8f)
					.freq(5)
					.currentStep(pool.currentIndex())	
				;


				new HOscillator()
					.target(d)
					.property(H.SCALE)
					.range(1, 5)
					.speed(0.8f)
					.freq(7)
					.currentStep(pool.currentIndex()*3)	
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






  public void settings() { 	size(640,640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_08_20" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
