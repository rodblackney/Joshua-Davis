import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import hype.*; 
import hype.extended.behavior.*; 
import hype.extended.colorist.*; 
import hype.extended.layout.*; 
import hype.interfaces.*; 
import ddf.minim.*; 
import ddf.minim.analysis.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class build_07 extends PApplet {










Minim       minim;
AudioPlayer myAudio;
FFT         myAudioFFT;

int         myAudioRange     = 256;
int         myAudioMax       = 100;

float       myAudioAmp       = 20.0f;
float       myAudioIndex     = 0.05f;
float       myAudioIndexAmp  = myAudioIndex;
float       myAudioIndexStep = 0.025f;

// ************************************************************************************

int   rectSize     = 2;

int   stageMargin  = 100;
int   stageWidth   = (myAudioRange * rectSize) + (stageMargin * 2);
int   stageHeight  = 700;

float xStart       = stageMargin; // 100 on x axis
float yStart       = stageMargin; // 100 / 100
int   xSpacing     = rectSize; // rect 2

// ************************************************************************************

int bgColor      = 0xff333333;

// ************************************************************************************

public void setup() {

	
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


public void draw() {
	// background(bgColor);

	myAudioFFT.forward(myAudio.mix);

	for (int i = 0; i < myAudioRange; ++i) {
		stroke(0); fill(255,5);

		float tempIndexAvg = (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		// float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		rect( xStart + (i*xSpacing), yStart, rectSize, tempIndexAvg);

		// show lines that visualize the rise of the amp
		stroke(0xff40A629); noFill();
		line(xStart + (i*xSpacing),yStart + ((i*(myAudioAmp+myAudioIndexAmp))/myAudioAmp),xStart + (i*xSpacing) + rectSize, yStart + ((i*(myAudioAmp+myAudioIndexAmp))/myAudioAmp));
		// attaches y 

		myAudioIndexAmp += myAudioIndexStep;
	}

	myAudioIndexAmp = myAudioIndex; // reset

	stroke(0xffFF3300); noFill();
	line(stageMargin, stageMargin+myAudioMax, width-stageMargin, stageMargin+myAudioMax);

	// show window mode
	noStroke(); fill(0);
	rect(stageMargin, (stageMargin/2)-10, 200, 20);
	fill(0xff0095a8); text("window : FFT.NONE", stageMargin+5, (stageMargin/2)+4);

	// song is done save an image and exit sketch
	if (!myAudio.isPlaying()) {
		saveFrame("../01_window_COSINE.png");
		exit();
	}
}


public void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}

  public void settings() { 	size(712, 712); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_07" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
