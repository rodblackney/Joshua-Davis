import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HOscillator rX, rY, rZ;
HColorPool colors;

ArrayList texList;
PImage[] pickedTex = new PImage[6];

int numCubes =20;
PVector[] pickedLoc = new PVector[numCubes];

float minScale = 40;
float maxScale = 100;
float[] boxSize = new float[numCubes];

color [] pickedColor = new color[6];

void setup() {
	size(600, 500, P3D);
	H.init(this).background(#FFFFFF).use3D(true);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616F, #FF3300, #FF6600);

	// load in texture images
	PImage t1 = loadImage("tex1.png"); // 1
	PImage t2 = loadImage("tex2.png"); // 2
	PImage t3 = loadImage("tex3.png"); // 3
	PImage t4 = loadImage("tex4.png"); // 4

	// push textures to array
	texList = new ArrayList();
	texList.add(t1);
	texList.add(t2);
	texList.add(t3);
	texList.add(t4);

	for (int i=0; i < pickedTex.length; i++) {
		pickedTex[i] = (PImage)texList.get( (int)random(texList.size()) );
	
		boxSize[i] = random(minScale, maxScale);

		pickedColor[i] = colors.getColor();

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

	for (int i = 0; i < numCubes; ++i) {

	pushMatrix();

		// translate to the center of the stage
		translate( pickedLoc[i].x, pickedLoc[i].y, pickedLoc[i].z );
		// uses processing rotates
		rotateX( radians(rX.curr()) );
		rotateY( radians(rY.curr()) );
		rotateZ( radians(rZ.curr()) );
		buildCube(i);

	popMatrix();
	}
}

void buildCube(int i) {

	strokeWeight(4); stroke(#000000);
	

		// Create a beginShape for each side

	// +Z "front" face
	fill(pickedColor[0]);
	beginShape(QUADS);vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[0] );vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 1);endShape();

	// -Z "back" face
	fill(pickedColor[1]);
	beginShape(QUADS);vertex( boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex(-boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex( boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[1] );vertex( boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex(-boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex( boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();

	// +Y "bottom" face
	fill(pickedColor[2]);
	beginShape(QUADS);vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[2] );vertex(-boxSize[i],  boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i],  boxSize[i],  boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();

	// -Y "top" face
	fill(pickedColor[3]);
	beginShape(QUADS);vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[3] );vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex( boxSize[i], -boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i], -boxSize[i],  boxSize[i], 0, 1);endShape();

	// +X "right" face
	fill(pickedColor[4]);
	beginShape(QUADS);vertex( boxSize[i], -boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex( boxSize[i],  boxSize[i],  boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[4] );vertex( boxSize[i], -boxSize[i],  boxSize[i], 0, 0);vertex( boxSize[i], -boxSize[i], -boxSize[i], 1, 0);vertex( boxSize[i],  boxSize[i], -boxSize[i], 1, 1);vertex( boxSize[i],  boxSize[i],  boxSize[i], 0, 1);endShape();

	// -X "left" face
	fill(pickedColor[5]);
	beginShape(QUADS);vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex(-boxSize[i], -boxSize[i],  boxSize[i], 1, 0);vertex(-boxSize[i],  boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();
	noFill();
	beginShape(QUADS);texture( pickedTex[5] );vertex(-boxSize[i], -boxSize[i], -boxSize[i], 0, 0);vertex(-boxSize[i], -boxSize[i],  boxSize[i], 1, 0);vertex(-boxSize[i],  boxSize[i],  boxSize[i], 1, 1);vertex(-boxSize[i],  boxSize[i], -boxSize[i], 0, 1);endShape();

}




