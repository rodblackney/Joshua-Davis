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

public class sketch_3_03 extends PApplet {



//import hype.extended.colorist.*;
//import hype.extended.layout.*;
//import hype.interfaces.*;

HCanvas c1;
HRect r1;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false).fade(6);
	H.add(c1);

	r1 = new HRect(50).rounding(10);
	c1.add(r1)
		.anchor(50, 125)
		.loc(width/2, height/2)
		.noStroke()
		.fill(0xffFF3300)
	;	
	new HRotate(r1, 2);
}

public void draw() {
	H.drawStage();
}

  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_3_03" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
