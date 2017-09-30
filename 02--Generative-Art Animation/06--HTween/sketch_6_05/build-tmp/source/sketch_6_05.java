import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_6_05 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HRect r1;
HTween t1;
HCallback tweenDone1, tweenDone2;

PVector pt1 = new PVector(320, 320, -400); // back
PVector pt2 = new PVector(320, 320, 100); // front

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true); // tell hype to use 3D
	

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchorAt(H.CENTER)
		.loc(pt1.x, pt1.y, pt1.z)
		.noStroke()
		.fill(0xffFF3300)
		;

	// start tween from left to right	
	t1 = new HTween()
		.target(r1)
		.property(H.LOCATION)
		.start( pt1.x, pt1.y, pt1.z )
		.end( pt2.x, pt2.y, pt2.z )
		.ease(0.05f)
		.spring(0.9f)
	;	

	// arrives at right position, move back to left position

	tweenDone1 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt2.x, pt2.y, pt1.z )
				.end( pt1.x, pt1.y, pt1.z )
				.ease(0.05f).spring(0.0f)
				.register().callback(tweenDone2)
				;
		}
	};


	// arrives at left position, move back to right position

	tweenDone2 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt1.x, pt1.y, pt1.z )
				.end( pt2.x, pt2.y, pt2.z )
				.ease(0.05f).spring(0.9f)
				.register().callback(tweenDone1)
				;
		}
	};

	t1.register().callback(tweenDone1);


}

public void draw() {
	H.drawStage();

	camera(mouseX, height/2, height / tan(PI/3), width/2, height/2, 0, 0, 1, 0);

//	noFill(); strokeWeight(2); stroke(#0095A8);
//	ellipse(pt1.x, pt1.y, 4, 4); // point a ellipse
//	ellipse(pt2.x, pt2.y, 4, 4); // point b ellipse
}



  public void settings() { 	size(640, 640, P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_6_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
