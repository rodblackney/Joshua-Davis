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

public class HRotate_10 extends PApplet {








public void setup() {

	
	H.init(this).background(0xff202020);
	

	HShape svg1 = new HShape("cog_sm.svg");
	H.add(svg1)
		.enableStyle(false)
		.anchorAt(H.CENTER)
		.loc(223, 413)
		.fill(0xffFF3300)
	;

	new HRotate(svg1, -0.5f);

	HShape svg2 = new HShape("cog_lg.svg");
	H.add(svg2)
		.enableStyle(false)
		.anchorAt(H.CENTER)
		.loc(690,260)
		.fill(0xffFF6600)
	;

	new HRotate(svg2, 0.3333f);
}

public void draw() {
	H.drawStage();
}




  public void settings() { 	size(640, 640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "HRotate_10" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
