import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_5_16 extends PApplet {



HImage d;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	for (int i = 0; i < 100; ++i) {
		d = new HImage("data/image.png");
		d

			.size( (int)random(75,175) )
			.rotate( (int)random(360) )
			.alpha( (int)random(50,255) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
			;
			H.add(d);
		}
	H.drawStage();
}
  public void settings() { 	size(600, 600); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_16" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
