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

public class build_20 extends PApplet {







HCanvas c1;
HPixelColorist colors;
HSwarm swarm;
HDrawablePool pool;
HTimer timer;

HOscillator rX,rY,rZ;

ArrayList texList;
PImage[] pickedTex = new PImage[6];

int numCubes = 13;
PVector[] pickedLoc = new PVector[numCubes];

float minScale = 50;
float maxScale = 150;
float[] boxSize = new float[numCubes];

PVector[] pickedRotation = new PVector[numCubes];

public void setup() {
	
//	H.init(this).background(#202020).use3D(true);
	H.init(this).background(0xff201942).autoClear(false);
	
	frameRate(30);

	colors = new HPixelColorist("color.jpg").fillOnly();

	c1 = new HCanvas(640,640).autoClear(false).fade(1);
	c1.x(-10000);
	H.add(c1);
	textureMode(NORMAL);

pool = new HDrawablePool(1);
	pool.autoParent(c1)
		.add (new HRect().rounding(4))

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					float rotation = random(TWO_PI);
					HDrawable d = (HDrawable) obj;
					d
						.size(100,2)
						.noStroke()
						.loc(320, 320)
						.rotationRad(rotation)
						.move (cos(rotation)*10, sin(rotation)*10)
						.anchorAt (H.CENTER)
					;

					colors.applyColor(d);

					new HSwarm()
						.addTarget(d)
						.addGoal(320, 320)
						.speed(10)
						.turnEase(0.017f)
						.twitch(5)
					;
				}
			}
		)
	;

	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(250)
		.callback (
			new HCallback() {
				public void run (Object obj) {
					pool.request();
				}
			}

		)
	;

	for (int i = 0; i < numCubes; ++i) {
		// picked location
		PVector pt = new PVector();
		pt.x = (int)random(-400,400);
		pt.y = (int)random(-400,400);
		pt.z = (int)random(-300,300);
		pickedLoc[i] = pt;

		// picked scale
		boxSize[i] = random(minScale, maxScale);

		// picked rotation 3D / x, y, z
		PVector pr = new PVector( (int)random(360), (int)random(360), (int)random(360) );
		pickedRotation[i] = pr;
	}

	rX = new HOscillator()
		.range(-360,360)
		.speed(0.05f)
		.freq(5)
	;

	rY = new HOscillator()
		.range(-360,360)
		.speed(0.05f)
		.freq(5)
	;

	rZ = new HOscillator()
		.range(-360,360)
		.speed(0.05f)
		.freq(5)
	;
}

public void draw() {

	pointLight(255, 255, 255, 0, height/2, 100);
	pointLight(255, 255, 255, width, height/2, 100);

	for(HDrawable d : pool) {
		if ( colors.getColor(d.x(), d.y() ) != 0)
		colors.applyColor(d);	
	}
	

	H.drawStage();

	rX.nextRaw();
	rY.nextRaw();
	rZ.nextRaw();

	pushMatrix();
		translate(width/2, height/2);
		rotateX( radians(rX.curr()) );
		rotateY( radians(rY.curr()) );
		rotateZ( radians(rZ.curr()) );

		for (int i = 0; i < numCubes; ++i) {
			pushMatrix();
				translate( pickedLoc[i].x, pickedLoc[i].y, pickedLoc[i].z );

				rotateX( radians(pickedRotation[i].x - frameCount) );
				rotateY( radians(pickedRotation[i].y + frameCount) );
				rotateZ( radians(pickedRotation[i].z) );

				buildCube(i);
			popMatrix();
		}
	popMatrix();
}

public void buildCube(int i) {
	strokeWeight(4); stroke(0xff666666);
	noStroke(); noFill();


	// +Z "front" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// -Z "back" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();

	// +Y "bottom" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();

	// -Y "top" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// +X "right" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// -X "left" face
	beginShape(QUADS);
		texture( c1.graphics() );
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 1, 0);
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();
}



  public void settings() { 	size(940,940,P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_20" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
