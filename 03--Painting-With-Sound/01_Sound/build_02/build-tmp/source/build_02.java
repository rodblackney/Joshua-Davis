import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 
import hype.interfaces.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 
import ddf.minim.effects.*; 
import ddf.minim.signals.*; 
import ddf.minim.spi.*; 
import ddf.minim.ugens.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build_02 extends PApplet {















int   myAudioRange = 256;

// ************************************************************************************

int   rectSize     = 2;

int   stageMargin  = 100;
int   stageWidth   = (myAudioRange * rectSize) + (stageMargin * 2);
int   stageHeight  = 300;

float xStart       = stageMargin; // 100 on x axis
float yStart       = stageMargin; // 100 / 100
int   xSpacing     = rectSize; // rect 2

// ************************************************************************************

int bgColor      = 0xff333333;

// ************************************************************************************

public void setup() {
	
	background(bgColor);
}

public void draw() {
	background(bgColor);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(0);
		rect( xStart + (i * xSpacing), yStart, rectSize, rectSize );

	}

	stroke(0xffFF3300); noFill();
	line(stageMargin, stageMargin + 100, width - stageMargin, stageMargin + 100);
}


  public void settings() { 	size(712, 300); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
