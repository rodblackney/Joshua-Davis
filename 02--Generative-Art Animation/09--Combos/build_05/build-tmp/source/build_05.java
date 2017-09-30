import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 
import hype.interfaces.*; 
import processing.video.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build_05 extends PApplet {







HOscillator rX, rY, rZ;


Movie tex;
int boxSize = 100;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true);
	

	tex = new Movie(this, "burst.mp4");
	tex.loop();
	textureMode(NORMAL);

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

	pushMatrix();
		// translate to the center of the stage
		translate(width/2, height/2, 0);

		// uses processing rotates
		rotateX( radians(rX.curr()) );
		rotateY( radians(rY.curr()) );
		rotateZ( radians(rZ.curr()) );

		buildCube();
	popMatrix();
}

public void buildCube() {

	strokeWeight(4); stroke(0xff000000);
	
	beginShape(QUADS);
		texture(tex);

		// v	float[]: vertex parameters, as a float array of length VERTEX_FIELD_COUNT
		// x	float: x-coordinate of the vertex
		// y	float: y-coordinate of the vertex
		// z	float: z-coordinate of the vertex
		// u	float: horizontal coordinate for the texture mapping
		// v	float: vertical coordinate for the texture mapping

		// +Z "front" face
		vertex(-boxSize, -boxSize*1.5f,  boxSize, 0, 0);
		vertex( boxSize*3, -boxSize,  boxSize, 1, 0);
		vertex( boxSize,  boxSize/2,  boxSize, 1, 1);
		vertex(-boxSize*5,  boxSize/2,  boxSize, 0, 1);

		// -Z "back" face
		//	vertex( boxSize, -boxSize, -boxSize, 0, 0);
		//	vertex(-boxSize, -boxSize, -boxSize, 1, 0);
		// vertex(-boxSize,  boxSize, -boxSize, 1, 1);
		// vertex( boxSize,  boxSize, -boxSize, 0, 1);

		// +Y "bottom" face
		// vertex(-boxSize,  boxSize,  boxSize, 0, 0);
		// vertex( boxSize,  boxSize,  boxSize, 1, 0);
		// vertex( boxSize,  boxSize, -boxSize, 1, 1);
		// vertex(-boxSize,  boxSize, -boxSize, 0, 1);

		// -Y "top" face
		// vertex(-boxSize, -boxSize, -boxSize, 0, 0);
		// vertex( boxSize, -boxSize, -boxSize, 1, 0);
		// vertex( boxSize, -boxSize,  boxSize, 1, 1);
		// vertex(-boxSize, -boxSize,  boxSize, 0, 1);

		// +X "right" face
		//	vertex( boxSize, -boxSize,  boxSize, 0, 0);
		//	vertex( boxSize, -boxSize, -boxSize, 1, 0);
		//	vertex( boxSize,  boxSize, -boxSize, 1, 1);
		//	vertex( boxSize,  boxSize,  boxSize, 0, 1);

		// -X "left" face
		//	vertex(-boxSize, -boxSize, -boxSize, 0, 0);
		//	vertex(-boxSize, -boxSize,  boxSize, 1, 0);
		//	vertex(-boxSize,  boxSize,  boxSize, 1, 1);
		//	vertex(-boxSize,  boxSize, -boxSize, 0, 1);

	endShape();

}

public void movieEvent(Movie m) {
	m.read();
}



  public void settings() { 	size(600, 500, P3D); 	smooth(); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_05" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
