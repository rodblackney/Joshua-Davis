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

public class build_07 extends PApplet {







HOscillator rX, rY, rZ;

int boxSize = 100;

ArrayList texList;
PImage[] pickedTex = new PImage[6];

int numCubes =10;
PVector[] pickedLoc = new PVector[numCubes];

public void setup() {
	
	H.init(this).background(0xff111111).use3D(true);
	

	// load in texture images
	PImage t1 = loadImage("tex1.jpg"); // 1
	PImage t2 = loadImage("tex2.jpg"); // 2
	PImage t3 = loadImage("tex3.jpg"); // 3
	PImage t4 = loadImage("tex4.jpg"); // 4
	PImage t5 = loadImage("tex5.jpg"); // 5

	// push textures to array
	texList = new ArrayList();
	texList.add(t1);
	texList.add(t2);
	texList.add(t3);
	texList.add(t4);
	texList.add(t5);

	for (int i=0; i < pickedTex.length; i++) {
		pickedTex[i] = (PImage)texList.get( (int)random(texList.size()) );
	}

	println( pickedTex);

	textureMode(NORMAL);

	for (int i = 0; i < numCubes; i++) {
		// picked location
		PVector pt = new PVector();
		// middle of the screen pick a random x pos
		pt.x = (width/2) + (int)random(-200,200);
		pt.y = (height/2) + (int)random(-200,200);
		// 
		pt.z = (int)random(-100,100);
		// use array of 10 PVectors
		pickedLoc[i] = pt;

	}



	rX = new HOscillator()
		.range(-360,360)
		.speed(0.1f)
		.freq(5)
	;

	rY = new HOscillator()
		.range(-360,360)
		.speed(0.1f)
		.freq(5)
	;

	rZ = new HOscillator()
		.range(-360,360)
		.speed(0.1f)
		.freq(5)
	;
}

public void draw() {
	H.drawStage();

	// next raw value
	rX.nextRaw();
	rY.nextRaw();
	rZ.nextRaw();

	for (int i = 0; i < numCubes; ++i) {

	pushMatrix();
		// translate to the center of the stage
		translate( pickedLoc[i].x, pickedLoc[i].y, pickedLoc[i].z );
		// uses processing rotates
		rotateX( radians(rX.curr()) );
		rotateY( radians(rY.curr()) );
		rotateZ( radians(rZ.curr()) );
		buildCube();
	popMatrix();
	}
}

public void buildCube() {

	strokeWeight(4); stroke(0xff000000);
	

		// Create a beginShape for each side

		// +Z "front" face
		beginShape(QUADS);
			texture( pickedTex[0] );
			vertex(-boxSize, -boxSize, boxSize, 0, 0);
			vertex( boxSize, -boxSize, boxSize, 1, 0);
			vertex( boxSize, boxSize, boxSize, 1, 1);
			vertex(-boxSize, boxSize, boxSize, 0, 1);
		endShape();

		// -Z "back" face
		beginShape(QUADS);
			texture( pickedTex[1] );
			vertex( boxSize, -boxSize, -boxSize, 0, 0);
			vertex(-boxSize, -boxSize, -boxSize, 1, 0);
			vertex(-boxSize,  boxSize, -boxSize, 1, 1);
			vertex( boxSize,  boxSize, -boxSize, 0, 1);
		endShape();

		// +Y "bottom" face
		beginShape(QUADS);
			texture( pickedTex[2] );
			vertex(-boxSize,  boxSize,  boxSize, 0, 0);
			vertex( boxSize,  boxSize,  boxSize, 1, 0);
			vertex( boxSize,  boxSize, -boxSize, 1, 1);
			vertex(-boxSize,  boxSize, -boxSize, 0, 1);
		endShape();

		// -Y "top" face
		beginShape(QUADS);
			texture( pickedTex[3] );
			vertex(-boxSize, -boxSize, -boxSize, 0, 0);
			vertex( boxSize, -boxSize, -boxSize, 1, 0);
			vertex( boxSize, -boxSize,  boxSize, 1, 1);
			vertex(-boxSize, -boxSize,  boxSize, 0, 1);
		endShape();

		// +X "right" face
		beginShape(QUADS);
			texture( pickedTex[4] );
			vertex( boxSize, -boxSize,  boxSize, 0, 0);
			vertex( boxSize, -boxSize, -boxSize, 1, 0);
			vertex( boxSize,  boxSize, -boxSize, 1, 1);
			vertex( boxSize,  boxSize,  boxSize, 0, 1);
		endShape();

		// -X "left" face
		beginShape(QUADS);
			texture( pickedTex[5] );
			vertex(-boxSize, -boxSize, -boxSize, 0, 0);
			vertex(-boxSize, -boxSize,  boxSize, 1, 0);
			vertex(-boxSize,  boxSize,  boxSize, 1, 1);
			vertex(-boxSize,  boxSize, -boxSize, 0, 1);
		endShape();


	endShape();

}




  public void settings() { 	size(600, 500, P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_07" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
