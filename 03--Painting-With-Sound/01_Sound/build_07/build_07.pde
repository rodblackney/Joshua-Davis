import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

import ddf.minim.*;
import ddf.minim.analysis.*;

Minim       minim;
AudioPlayer myAudio;
FFT         myAudioFFT;

int         myAudioRange     = 256;
int         myAudioMax       = 100;

float       myAudioAmp       = 20.0;
float       myAudioIndex     = 0.05;
float       myAudioIndexAmp  = myAudioIndex;
float       myAudioIndexStep = 0.025;

// ************************************************************************************

int   rectSize     = 2;

int   stageMargin  = 100;
int   stageWidth   = (myAudioRange * rectSize) + (stageMargin * 2);
int   stageHeight  = 700;

float xStart       = stageMargin; // 100 on x axis
float yStart       = stageMargin; // 100 / 100
int   xSpacing     = rectSize; // rect 2

// ************************************************************************************

color bgColor      = #333333;

// ************************************************************************************

void setup() {

	size(712, 712);
	background(bgColor);

	minim = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	// myAudio.play(); // repeat play
	myAudio.play(); // play once

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);

	/*

	https://en.wikipedia.org/wiki/Window_function

	myAudioFFT.window(FFT.NONE);

	myAudioFFT.window(FFT.BARTLETT);
	myAudioFFT.window(FFT.BARTLETTHANN);
	myAudioFFT.window(FFT.BLACKMAN);
	myAudioFFT.window(FFT.COSINE);
	myAudioFFT.window(FFT.GAUSS);
	myAudioFFT.window(FFT.HAMMING);
	myAudioFFT.window(FFT.HANN);
	myAudioFFT.window(FFT.LANCZOS);
	myAudioFFT.window(FFT.TRIANGULAR);

	*/

	myAudioFFT.window(FFT.COSINE);

}


void draw() {
	// background(bgColor);

	myAudioFFT.forward(myAudio.mix);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(255,5);

		float tempIndexAvg = (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		// float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		rect( xStart + (i*xSpacing), yStart, rectSize, tempIndexAvg);

		// show lines that visualize the rise of the amp
		stroke(#40A629); noFill();
		line(xStart + (i*xSpacing),yStart + ((i*(myAudioAmp+myAudioIndexAmp))/myAudioAmp),xStart + (i*xSpacing) + rectSize, yStart + ((i*(myAudioAmp+myAudioIndexAmp))/myAudioAmp));
		// attaches y 

		myAudioIndexAmp += myAudioIndexStep;
	}

	myAudioIndexAmp = myAudioIndex; // reset

	stroke(#FF3300); noFill();
	line(stageMargin, stageMargin+myAudioMax, width-stageMargin, stageMargin+myAudioMax);

	// show window mode
	noStroke(); fill(0);
	rect(stageMargin, (stageMargin/2)-10, 200, 20);
	fill(#0095a8); text("window : FFT.NONE", stageMargin+5, (stageMargin/2)+4);

	// song is done save an image and exit sketch
	if (!myAudio.isPlaying()) {
		// saveFrame("../01_window_COSINE.png");
		saveFrame("01_window_COSINE.png");
		exit();
	}
}


void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}

