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

boolean 	showVisualizer 	= true;

int 		myAudioRange		= 11;
int 		myAudioMax 			= 100;

float 		myAudioAmp		= 40.0;
float 		myAudioIndex		= 0.2;
float 		myAudioIndexAmp	= myAudioIndex;
float 		myAudioIndexSetup	= 0.35;

float[] myAudioData		= new float [myAudioRange];

HDrawablePool pool;

void setup() {
	size(700, 700);
	H.init(this).background(#202020);

	// minim
	minim = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	// FFT
	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	myAudioFFT.window(FFT.GAUSS);

	// hype
	pool = new HDrawablePool(myAudioRange);
	pool.autoAddToStage()
		.add ( new HRect(100).rounding(10) )
		.layout (new HGridLayout().startX(100).startY(height/2).spacingX(50).cols(myAudioRange))		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.stroke(0)
						.fill(255, 255)
						.anchorAt(H.CENTER)
						.rotation(45)
					;
				}
			}
		)
		.requestAll()
	;
}

void draw() {
	myAudioFFT.forward(myAudio.mix);
	myAudioDataUpdate();

	H.drawStage();

	int i = 0;

	for (HDrawable d : pool) {

		int fftFillColor = (int)map(myAudioData[i], 0, myAudioMax, 0, 255);
		d.fill(fftFillColor, 225);
		i++;
	}

	// call widget
	if (showVisualizer) myAudioDataWidget();
}

void myAudioDataUpdate() {

	for (int i = 0; i < myAudioRange; ++i) {
		float tempIndexAvg 	= (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		myAudioData[i]		= tempIndexCon;
		myAudioIndexAmp += myAudioIndexSetup;
	}
	myAudioIndexAmp = myAudioIndex;
}

void myAudioDataWidget() {
	// noLights();
	// hint(DISABLE_DEPTH_TEST);
	noStroke(); fill(0,200); rect(0, height-112, width, 102);

	for (int i = 0; i < myAudioRange; ++i) {
		if 		(i==0) 	fill(#237D26); // base / sub item 0
		else if (i==3) 	fill(#80C41C); // snare / sub item 3
		else			fill(#CCCCCC); // others

		rect(10 + (i*5), (height-myAudioData[i])-11, 4, myAudioData[i]);
	}
	// hint(ENABLE_DEPTH_TEST);
}

void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}










