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


int myAudioRange = 256;

// int   rectSize     = 2;

int   stageMargin  = 100;
int   stageWidth   = (myAudioRange * rectSize) + (stageMargin * 2);
int   stageHeight  = 300;

float xStart       = stageMargin;
float yStart       = stageMargin;
int   xSpacing     = rectSize;

color bgColor      = #333333;

void setup() {
	size(stageWidth, stageHeight);
	background(bgColor);
}

void draw() {
	background(bgColor);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(255);
		rect( xStart + (i*xSpacing), yStart, rectSize, rectSize );
	}

	stroke(#FF3300); noFill();
	line(0, stageMargin+100, width, stageMargin+100);

}