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

public class sketch_4_03 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HFollow mf;
HRect r1;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	r1 = new HRect(100).rounding(40);
	r1
		.noStroke()
		.fill(0xffECECEC)
		.loc(width/2, height/2)
		.anchorAt(H.CENTER)
		.rotation(45)
	;

	H.add(r1);

	// - new HFollow(float ease)

	mf = new HFollow()
		.target(r1)
		.ease(0.1f)
		.spring(0.75f)
		;



}

public void draw() {
	H.drawStage();
	
}
  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_4_03" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
