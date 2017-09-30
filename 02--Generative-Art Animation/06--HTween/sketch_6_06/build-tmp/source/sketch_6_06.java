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

public class sketch_6_06 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// behaviour stacking

HRect r1;
HTween t1, t2;
HCallback tweenDone1, tweenDone2;
HCallback rotateDone1, rotateDone2;

PVector pt1 = new PVector(160, 320); // left
PVector pt2 = new PVector(480, 320); // right

public void setup() {
	
	H.init(this).background(0xff202020);
	

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1).anchorAt(H.CENTER).loc(pt1.x, pt1.y).noStroke().fill(0xffFF3300)
		;

	// left to right loop
	t1 = new HTween()
		.target(r1).property(H.LOCATION).start( pt1.x, pt1.y, pt1.z ).end( pt2.x, pt2.y, pt2.z ).ease(0.02f).spring(0.7f)
		;	

	tweenDone1 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt2.x, pt2.y)
				.end( pt1.x, pt1.y)
				.register().callback(tweenDone2)
				;
		}
	};

	tweenDone2 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt1.x, pt1.y)
				.end( pt2.x, pt2.y)
				.register().callback(tweenDone1)
				;
		}
	};


// Rotate Loop 0 to 80

t2 = new HTween().target(r1).property(H.ROTATION).start( 0 ).end( 180 ).ease(0.02f).spring(0.7f);

rotateDone1 = new HCallback() {
	public void run(Object obj) {
		t2.start(180).end(0).register().callback(rotateDone2);
	}
};


rotateDone2 = new HCallback() {
	public void run(Object obj) {
		t2.start(0).end(180).register().callback(rotateDone1);
	}
};


	t1.register().callback(tweenDone1);
	t2.register().callback(rotateDone1);

}

public void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
	ellipse(pt1.x, pt1.y, 4, 4); // point a ellipse
	ellipse(pt2.x, pt2.y, 4, 4); // point b ellipse
}



  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_6_06" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
