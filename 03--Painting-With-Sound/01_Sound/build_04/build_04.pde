import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

import ddf.minim.*;
import ddf.minim.analysis.*;

Minim 		minim;
AudioPlayer	myAudio;
FFT			myAudioFFT;

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

	minim = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop(); // repeat play
	// myAudio.play(); // play once

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);

}

void draw() {
	background(bgColor);

	myAudioFFT.forward(myAudio.mix);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(255);

		float tempIndexAvg = myAudioFFT.getAvg(i);
		rect( xStart + (i * xSpacing), yStart, rectSize, tempIndexAvg );

		}

	stroke(#FF3300); noFill();
	line(stageMargin, stageMargin + 100, width - stageMargin, stageMargin + 100);
}

void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}

