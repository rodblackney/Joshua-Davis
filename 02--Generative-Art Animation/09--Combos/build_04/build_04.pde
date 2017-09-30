import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HOscillator rX, rY, rZ;

import processing.video.*;
Movie tex;
int boxSize = 100;

void setup() {
	size(600, 500, P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

	tex = new Movie(this, "burst.mp4");
	tex.loop();
	textureMode(NORMAL);

	rX = new HOscillator()
		.range(-360,360)
		.speed(0.1)
		.freq(5)
	;

	rY = new HOscillator()
		.range(-360,360)
		.speed(0.1)
		.freq(5)
	;

	rZ = new HOscillator()
		.range(-360,360)
		.speed(0.1)
		.freq(5)
	;
}

void draw() {
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

void buildCube() {

	strokeWeight(4); stroke(#000000);
	
	beginShape(QUADS);
		texture(tex);

		// v	float[]: vertex parameters, as a float array of length VERTEX_FIELD_COUNT
		// x	float: x-coordinate of the vertex
		// y	float: y-coordinate of the vertex
		// z	float: z-coordinate of the vertex
		// u	float: horizontal coordinate for the texture mapping
		// v	float: vertical coordinate for the texture mapping

		// +Z "front" face
		vertex(-boxSize, -boxSize,  boxSize, 0, 0);
		vertex( boxSize, -boxSize,  boxSize, 1, 0);
		vertex( boxSize,  boxSize,  boxSize, 1, 1);
		vertex(-boxSize,  boxSize,  boxSize, 0, 1);

		// -Z "back" face
		vertex( boxSize, -boxSize, -boxSize, 0, 0);
		vertex(-boxSize, -boxSize, -boxSize, 1, 0);
		vertex(-boxSize,  boxSize, -boxSize, 1, 1);
		vertex( boxSize,  boxSize, -boxSize, 0, 1);

		// +Y "bottom" face
		vertex(-boxSize,  boxSize,  boxSize, 0, 0);
		vertex( boxSize,  boxSize,  boxSize, 1, 0);
		vertex( boxSize,  boxSize, -boxSize, 1, 1);
		vertex(-boxSize,  boxSize, -boxSize, 0, 1);

		// -Y "top" face
		vertex(-boxSize, -boxSize, -boxSize, 0, 0);
		vertex( boxSize, -boxSize, -boxSize, 1, 0);
		vertex( boxSize, -boxSize,  boxSize, 1, 1);
		vertex(-boxSize, -boxSize,  boxSize, 0, 1);

		// +X "right" face
		vertex( boxSize, -boxSize,  boxSize, 0, 0);
		vertex( boxSize, -boxSize, -boxSize, 1, 0);
		vertex( boxSize,  boxSize, -boxSize, 1, 1);
		vertex( boxSize,  boxSize,  boxSize, 0, 1);

		// -X "left" face
		vertex(-boxSize, -boxSize, -boxSize, 0, 0);
		vertex(-boxSize, -boxSize,  boxSize, 1, 0);
		vertex(-boxSize,  boxSize,  boxSize, 1, 1);
		vertex(-boxSize,  boxSize, -boxSize, 0, 1);

	endShape();

}

void movieEvent(Movie m) {
	m.read();
}



