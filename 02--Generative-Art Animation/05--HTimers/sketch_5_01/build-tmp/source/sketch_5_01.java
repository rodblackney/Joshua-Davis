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

public class sketch_5_01 extends PApplet {




// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HTimer timer;
int counter = 1;

public void setup() {
	
	H.init(this).background(0xff202020);
	

	timer = new HTimer()
		.numCycles(10) // timer length
		.interval(500) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					println("hello, world - " + counter);
					++counter;
				}
			}
		)
	;
}

public void draw() {
	H.drawStage();
}

  public void settings() { 	size(640,640); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_01" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
