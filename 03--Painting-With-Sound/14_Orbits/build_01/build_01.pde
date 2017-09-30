import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

import ddf.minim.*;
import ddf.minim.analysis.*;

Minim 			minim;
AudioPlayer		myAudio;
FFT				myAudioFFT;

boolean 		showVisualizer 		= false;

int 			myAudioRange		= 11;
int 			myAudioMax 			= 100;

float 			myAudioAmp			= 40.0;
float 			myAudioIndex		= 0.2;
float 			myAudioIndexAmp		= myAudioIndex;
float 			myAudioIndexStep	= 0.35;

float[] myAudioData				= new float [myAudioRange];


HDrawablePool pool;
int poolMax 		= 6;

					// red 		green
color[] palette 	= { #FF0000,	#00FF00, #0000FF, #00FFFF, #FFFF00, #FF00FF};

void setup() {
	size(700, 700, OPENGL);
	H.init(this).background(#202020).use3D(true).autoClear(true);

	minim   = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	myAudioFFT.window(FFT.GAUSS);

	pool = new HDrawablePool(poolMax);
	pool.autoAddToStage()
		.add ( new HEllipse(10) )
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					int i = pool.currentIndex();

					HDrawable d = (HDrawable) obj;
					d
						.noStroke()
						.fill(palette[i])
						.anchorAt(H.CENTER)
					;

					HOrbiter3D orb = new HOrbiter3D(width/2, height/2, 0)
						.target(d)
						.zSpeed( random(-2, 2)+0.1 )
						.ySpeed( random(-1, 1)+0.1 )
						.radius(250)
						.zAngle( (int)random(360) )
						.yAngle( (int)random(360) )
					;

					d.extras( new HBundle().num("i", i) );

				}
			}
		)
		.requestAll()
	;
}

void draw() {
	myAudioFFT.forward(myAudio.mix);
	myAudioDataUpdate();

	noLights();
	H.drawStage();
	// sphereDetail(20);

	pointLight(0, 149, 168, width/2, 0,      -100); // TEAL
	pointLight(255, 51, 0,  width/2, height,    0); // ORANGE
	pointLight(51, 51, 51,  width/2, 150,     200); // GRAY

	// draw sphere in the center

	pushMatrix();
		translate(width/2, height/2, 0);
		sphereDetail(75);
		noStroke(); fill(255); sphere(125); noStroke(); noFill();
	popMatrix();


	if (showVisualizer) myAudioDataWidget();
}

void myAudioDataUpdate() {

	for (int i = 0; i < myAudioRange; ++i) {
		float tempIndexAvg 	= (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		myAudioData[i]		= tempIndexCon;
		myAudioIndexAmp+=myAudioIndexStep;
	}
	myAudioIndexAmp = myAudioIndex;
}

void myAudioDataWidget() {
	noLights();
	hint(DISABLE_DEPTH_TEST);
	noStroke(); fill(0,200); rect(0, height-112, width, 102);

	for (int i = 0; i < myAudioRange; ++i) {
		if 		(i==0) 	fill(#237D26); // base / sub item 0
		else if (i==3) 	fill(#80C41C); // snare / sub item 3
		else			fill(#CCCCCC); // others

		rect(10 + (i*5), (height-myAudioData[i])-11, 4, myAudioData[i]);
	}
	hint(ENABLE_DEPTH_TEST);
}

void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}










