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

color bgColor      = #333333;

// ************************************************************************************

void setup() {
	size(712, 300);
	background(bgColor);
}

void draw() {
	background(bgColor);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(255);
		rect( xStart + (i * xSpacing), yStart, rectSize, rectSize );
		
	}

	stroke(#FF3300); noFill();
	line(stageMargin, stageMargin + 100, width - stageMargin, stageMargin + 100);
}


