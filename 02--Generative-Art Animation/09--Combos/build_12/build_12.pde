import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HOscillator rX, rY, rZ;
HPixelColorist colors;

ArrayList texList;
PImage[] pickedTex = new PImage[6];

int numCubes = 20;
PVector[] pickedLoc = new PVector[numCubes];

float minScale = 10;
float maxScale = 80;
float[] boxSize = new float[numCubes];

color [] pickedColor = new color[numCubes];

void setup() {
	size(600, 500, P3D);
	H.init(this).background(#111111).use3D(true);
	smooth();

	// load in texture images
	PImage t1 = loadImage("tex1.jpg"); // 1
	PImage t2 = loadImage("tex2.jpg"); // 2
	PImage t3 = loadImage("tex3.jpg"); // 3
	PImage t4 = loadImage("tex4.jpg"); // 4

	// push textures to array
	texList = new ArrayList();
	texList.add(t1);
	texList.add(t2);
	texList.add(t3);
	texList.add(t4);

	for (int i=0; i < pickedTex.length; i++) {
		pickedTex[i] = (PImage)texList.get( (int)random(texList.size()) );
	
		boxSize[i] = random(minScale, maxScale);
	}

	println( pickedTex);

	textureMode(NORMAL);

	for (int i = 0; i < numCubes; i++) {
		// picked location
		PVector pt = new PVector();
		// middle of the screen pick a random x pos
		pt.x = (int)random(-300,300);
		pt.y = (int)random(-300,300);
		pt.z = (int)random(-300,300);
		// use array of 10 PVectors
		pickedLoc[i] = pt;

		boxSize[i] = random(minScale, maxScale);

	}

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
		translate(width/2, height/2);
		rotateX( radians(rX.curr()) );
		rotateY( radians(rY.curr()) );
		rotateZ( radians(rZ.curr()) );

		for (int i = 0; i < numCubes; ++i) {
			pushMatrix();
				translate( pickedLoc[i].x, pickedLoc[i].y, pickedLoc[i].z );
				buildCube(i);
			popMatrix();
		}

	popMatrix();

	}


	

void buildCube(int i) {

	strokeWeight(4); stroke(#000000);
	
		// +Z "front" face
	beginShape(QUADS);
		texture( pickedTex[0] );
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// -Z "back" face
	beginShape(QUADS);
		texture( pickedTex[1] );
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();

	// +Y "bottom" face
	beginShape(QUADS);
		texture( pickedTex[2] );
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();

	// -Y "top" face
	beginShape(QUADS);
		texture( pickedTex[3] );
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// +X "right" face
	beginShape(QUADS);
		texture( pickedTex[4] );
		vertex( boxSize[i], -boxSize[i],  boxSize[i], 0, 0);
		vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);
		vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);
		vertex( boxSize[i],  boxSize[i],  boxSize[i], 0, 1);
	endShape();

	// -X "left" face
	beginShape(QUADS);
		texture( pickedTex[5] );
		vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);
		vertex(-boxSize[i], -boxSize[i],  boxSize[i], 1, 0);
		vertex(-boxSize[i],  boxSize[i],  boxSize[i], 1, 1);
		vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);
	endShape();

}




