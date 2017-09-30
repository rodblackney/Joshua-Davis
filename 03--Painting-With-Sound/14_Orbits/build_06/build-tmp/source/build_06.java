import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build_06 extends PApplet {



float yoff = 0.0f;
float increasingValue = 0.006f;
float mapping;

float offset = 2;
int segment = 5;

int startValue = 90, endValue = 300;
float totalWidth = 280;

float drawingBound;

public void setup() {
  
  colorMode(HSB,360,100,100,1);
  frameRate(24);

  
  
  background(0xff1A2841, 1);
  noStroke();
  noiseSeed(0);

  drawingBound = (totalWidth) - (offset * 2);
  translate((width - totalWidth)*0.5f, 0);
}

public void draw() {
  fill(0xff1A2841, 1);
  rect(0, 0, width, height);
  translate((width - totalWidth)*0.5f,0);

  beginShape();
  float xoff = 0;
  float yPos;

  for(int i = 0; i < segment; i++){
    for(float j = (offset) + ((drawingBound/segment) * i); j <= ((drawingBound/segment) * (i+1)) - offset; j+= 1) {
      noiseDetail(12, 0.2f);
      pushStyle();

      // pretty colours!
      fill(120/(i+2), (100 - (j%5)), 90, 1);

      mapping = map(noise(xoff, yoff), 0, 1, startValue, endValue);
      yPos = mapping;
      vertex(j, yPos);
      xoff += increasingValue;
    }

    yoff += increasingValue;
    vertex((totalWidth/2), height - 40);

    // End of the shape
    endShape(CLOSE);
    popStyle();

    if(endValue < height){
      endValue += 0.1f;
      startValue += 0.1f;
    }
  }

}

public void mouseClicked() {
  println("saved");
  saveFrame("focus/focus-###.png");
}
  public void settings() {  size(400, 300, OPENGL);  smooth(8);  pixelDensity(displayDensity()); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_06" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
