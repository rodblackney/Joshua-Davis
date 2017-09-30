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

public class sketch_4_01 extends PApplet {

public void setup() {

	

	rotate(radians(10));
	fill(255);
	rect(400, 100, 50, 50);
	noFill();
	rect(0, 0, 600, 600);

	rotate(radians(10));
	fill(255);
	rect(400, 100, 50, 50);
	noFill();
	rect(0, 0, 600, 600);

	rotate(radians(10));
	fill(255);
	rect(400, 100, 50, 50);
	noFill();
	rect(0, 0, 600, 600);

	rotate(radians(10));
	fill(255);
	rect(400, 100, 50, 50);
	noFill();
	rect(0, 0, 600, 600);

	rotate(radians(10));
	fill(255);
	rect(400, 100, 50, 50);
	noFill();
	rect(0, 0, 600, 600);


}

public void draw() {
	
}
  public void settings() { 	size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_4_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
