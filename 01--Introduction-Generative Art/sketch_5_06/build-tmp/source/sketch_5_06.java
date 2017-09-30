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

public class sketch_5_06 extends PApplet {



// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;


// hype adjusting registration point

HRect d1, d2, d3, d4, d5;

public void setup() {
	

	// use the hype library
	// specify background color
	// use boolean autoClear for background clearing

	H.init(this).background(0xff666666).autoClear(false);

	// d1 rectangle
	d1 = new HRect();

	d1.size(50)
		.rotation(45)
		.anchor(5, 5)
		.loc(100, height/2);
	d1.fill( color (random(255), random(255), random(255)) );
	d1.noStroke();

	HRotate r1 = new HRotate()
		.target(d1)
		.speed(-1);
	H.add(d1);

	// d2 rectangle
	d2 = new HRect();
	d2.size(50)
	.rotation(45)
	.anchor(10, 10)
	.loc(200, height/2);
	HRotate r2 = new HRotate().target(d2).speed(2);
	H.add(d2);
	d2.fill( color (random(255), random(255), random(255)) );	

	// d3 rectangle
	d3 = new HRect();
	d3.size(50)
	.rotation(45)
	.anchor(15, 15)
	.loc(300, height/2);
	HRotate r3 = new HRotate().target(d3).speed(3);
	H.add(d3);
	d3.fill( color (random(255), random(255), random(255)) );
	d3.noStroke();


	// d4 rectangle
	d4 = new HRect();
	d4.size(50)
	.rotation(45)
	.anchor(25, 25)
	.loc(400, height/2);
	HRotate r4 = new HRotate().target(d4).speed(2.5f);
	H.add(d4);
	d4.fill( color (random(255), random(255), random(255)) );	

	// d5 rectangle
	d5 = new HRect();
	d5.size(50)
	.rotation(45)
	.anchor(25, -25)
	.loc(500, height/2);
	HRotate r5 = new HRotate().target(d5).speed(5);
	H.add(d5);
	d5.fill( color (random(255), random(255), random(255)) );	

}

public void draw() {

	// automatically clears the stage
	H.drawStage();

	line(0, height/2, width, height/2);

	ellipse(100, height/2, 5, 5);
	ellipse(200, height/2, 5, 5);
	ellipse(300, height/2, 5, 5);
	ellipse(400, height/2, 5, 5);
	ellipse(500, height/2, 5, 5);


	
}
  public void settings() { 	size(600, 600); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_5_06" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
