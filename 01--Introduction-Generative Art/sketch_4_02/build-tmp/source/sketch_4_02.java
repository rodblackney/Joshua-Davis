import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_4_02 extends PApplet {

public void setup() {

	
	rectMode(CENTER);

	pushMatrix();
		translate(100, height/2);
		rotate(radians(45));
		rect(0, 0, 50, 50);
	popMatrix();

	pushMatrix();
		translate(200, height/2);
		rotate(radians(45));
		rect(0, 0, 50, 50);
	popMatrix();

	pushMatrix();
		translate(300, height/2);
		rotate(radians(45));
		rect(0, 0, 50, 50);
	popMatrix();

	pushMatrix();
		translate(400, height/2);
		rotate(radians(45));
		rect(0, 0, 50, 50);
	popMatrix();

	pushMatrix();
		translate(500, height/2);
		rotate(radians(45));
		rect(0, 0, 50, 50);
	popMatrix();

	// draws a line in the middle of the sketch
	line(0, height/2, width, height/2);

	ellipse(100, height/2, 5, 5);
	ellipse(200, height/2, 5, 5);
	ellipse(300, height/2, 5, 5);
	ellipse(400, height/2, 5, 5);
	ellipse(500, height/2, 5, 5);


}
  public void settings() { 	size(600, 500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_4_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
