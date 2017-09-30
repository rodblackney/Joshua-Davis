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

boolean 	showVisualizer 	= false;

int 		myAudioRange		= 11;
int 		myAudioMax 			= 100;

float 		myAudioAmp		= 40.0;
float 		myAudioIndex		= 0.2;
float 		myAudioIndexAmp	= myAudioIndex;
float 		myAudioIndexSetup	= 0.35;

float[] myAudioData		= new float [myAudioRange];



HDrawablePool pool;
int poolMax = 100;
					// base orange				// snare blue
color [] palette = {#FF3300,#FF620C,#FF9519,	#0095A8, #FFC725,#F8EF33,#FFFF33,#CCEA4A,#9AD561,#64BE7A,#2EA893};


void setup() {
	size(700, 700);
	H.init(this).background(#202020).autoClear(false);

	// minim
	minim = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	// FFT
	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	myAudioFFT.window(FFT.GAUSS);

	// hype
	pool = new HDrawablePool(poolMax);
	pool.autoAddToStage()
		.add ( new HImage("block.png") )
		.onCreate (
			new HCallback() {
				public void run(Object obj) {

					int ranIndex = (int)random(myAudioRange);
					HDrawable d = (HDrawable) obj;
					d
						.stroke(0)
						.fill(palette[ranIndex], 255)
						.anchorAt(H.CENTER)
						.rotation(45)
						.loc( (int)random(width), (int)random(height) )
						.extras( new HBundle().num("i", ranIndex) )
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

	for (HDrawable d : pool) {
		HBundle tempExtra = d.extras();
		int i = (int)tempExtra.num("i");

		int fftSize;
		if (i==0 || i==3) fftSize = (int)map(myAudioData[i], 0, myAudioMax, 0, 300); // base and snare  / subitem 0 & 3
		else              fftSize = (int)map(myAudioData[i], 0, myAudioMax, 0, 100); // all others
		d.size(fftSize);

		// visualize under diamonds which random numbers got picked

		if (i==0) {
			fill(#237D26); // base  / subitem 0
		} else if (i==3) {
			fill(#80C41C); // snare / subitem 3
			d.y(height/2-150);
		} else {
			fill(#FF3300); // all others
		}

		// textSize(12); textAlign(CENTER); text(i, d.x(), d.y() );
	}

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










