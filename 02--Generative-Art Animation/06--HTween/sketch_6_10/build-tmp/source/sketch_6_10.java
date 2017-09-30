import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_6_10 extends PApplet {





// import hype.extended.layout.*;
// import hype.interfaces.*;

// behaviour stacking
// nested rectangles r1, r2, r3

HPixelColorist colors;

HRect r1;
HTween t1;

PVector pt1 = new PVector(160,160); // top, left
PVector pt2 = new PVector(480,160); // top, right
PVector pt3 = new PVector(480,480); // bottom, right
PVector pt4 = new PVector(160,480); // bottom, left


HCallback cb1, cb2, cb3, cb4;

public void setup() {
	
	H.init(this).background(0xff202020).autoClear(false);
	

	colors = new HPixelColorist("color.jpg").fillOnly();

	// rectangle settings
	r1 = new HRect(150).rounding(25);
	H.add(r1).anchorAt(H.CENTER).loc(pt1.x, pt1.y).noStroke().fill(0xffFF3300);

	t1 = new HTween().target(r1).property(H.LOCATION).start( pt1.x, pt1.y ).end( pt2.x, pt2.y ).ease(0.03f).spring(0.7f);

	// position 1 - 2
	cb1 = new HCallback() {
		public void run(Object obj) {
			t1.start(pt1.x, pt1.y).end(pt2.x, pt2.y).register().callback(cb2);
		}
	};

	// position 2 - 3
	cb2 = new HCallback() {
		public void run(Object obj) {
			t1.start(pt2.x, pt2.y).end(pt3.x, pt3.y).register().callback(cb3);
		}
	};

	// position 3 - 4
	cb3 = new HCallback() {
		public void run(Object obj) {
			t1.start(pt3.x, pt3.y).end(pt4.x, pt4.y).register().callback(cb4);
		}
	};

	// position 4 - 1
	cb4 = new HCallback() {
		public void run(Object obj) {
			t1.start(pt4.x, pt4.y).end(pt1.x, pt1.y).register().callback(cb1);
		}
	};
			

	t1.register().callback(cb2);
}

public void draw() {
	// update color 
	colors.applyColor(r1);
	H.drawStage();

	noFill(); strokeWeight(2); stroke(0xff0095A8);
//	ellipse(pt1.x, pt1.y, 4, 4); // point a ellipse
//	ellipse(pt2.x, pt2.y, 4, 4); // point b ellipse
//	ellipse(pt3.x, pt3.y, 4, 4); // point b ellipse
//	ellipse(pt4.x, pt4.y, 4, 4); // point b ellipse
}



  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_6_10" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
