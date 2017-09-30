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

public class sketch_3_05 extends PApplet {



//import hype.extended.colorist.*;
//import hype.extended.layout.*;
//import hype.interfaces.*;

HCanvas c1, c2, c3;
HRect r1, r2, r3;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	c1 = new HCanvas().autoClear(false).fade(2);
	c2 = new HCanvas().autoClear(false).fade(2);
	c3 = new HCanvas().autoClear(false).fade(2);

	H.add(c1);
	H.add(c2);
	H.add(c3);

	// each rectangle drawn to it's own HCanvas

	r1 = new HRect(100).rounding(10);
	c1.add(r1)
		.loc(width/2 -160, height/2)
		.noStroke()
		.fill(0xffFF3300)
	;	

	r2 = new HRect(100).rounding(10);
	c2.add(r2)
		.loc(width/2, height/2)
		.noStroke()
		.fill(0xff0095A8)
	;

	r3 = new HRect(100).rounding(10);
	c3.add(r3)
		.loc(width/2 + 160, height/2)
		.noStroke()
		.fill(0xff6666FF)
	;


	new HRotate(r1, 2);
	new HRotate(r2, 2);
	new HRotate(r3, 2);


}

public void draw() {

	H.drawStage();

}

  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_3_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
