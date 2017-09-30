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

public class build_02 extends PApplet {










Minim 		minim;
AudioPlayer	myAudio;
FFT			myAudioFFT;

boolean 	showVisualizer 		= true;

int 		myAudioRange		= 11;
int 		myAudioMax 			= 100;

float 		myAudioAmp			= 40.0f;
float 		myAudioIndex		= 0.2f;
float 		myAudioIndexAmp		= myAudioIndex;
float 		myAudioIndexStep	= 0.35f;

float[] myAudioData				= new float [myAudioRange];



HDrawablePool pool;

int poolCols	= 7;
int poolRows	= 7;

					// base orange				// snare blue
int [] palette = {0xffFF3300,0xffFF620C,0xffFF9519,	0xff0095A8, 0xffFFC725,0xffF8EF33,0xffFFFF33,0xffCCEA4A,0xff9AD561,0xff64BE7A,0xff2EA893};


public void setup() {
	
	H.init(this).background(0xff202020).use3D(true).autoClear(true);

	minim   = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	// myAudioFFT.window(FFT.GAUSS);

	pool = new HDrawablePool(poolCols*poolRows);
	pool.autoAddToStage()
		.add ( new HBox() )
		.layout (new HGridLayout().startX(110).startY(110).spacing(80, 80).cols(poolCols))
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					int ranIndex = (int)random(myAudioRange);

					HBox d = (HBox) obj;
					d
						.depth(500)
						.width(50)
						.height(50)
						.noStroke()
						.fill(palette[ranIndex], 225)
						.z(-900)
						.extras( new HBundle().num("i", ranIndex) )
					;
				}
			}
		)
		.requestAll()
	;
}

public void draw() {
	myAudioFFT.forward(myAudio.mix);
	myAudioDataUpdate();

	lights();
	H.drawStage();

	 for (HDrawable d : pool) {
		HBundle tempExtra = d.extras();
		int i = (int)tempExtra.num("i");

		int fftZ	= (int)map(myAudioData[i], 0, myAudioMax, -900, 100);
		d.z(fftZ);

	}

	if (showVisualizer) myAudioDataWidget();
}


public void myAudioDataUpdate() {

	for (int i = 0; i < myAudioRange; ++i) {
		float tempIndexAvg 	= (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		myAudioData[i]		= tempIndexCon;
		myAudioIndexAmp+=myAudioIndexStep;
	}
	myAudioIndexAmp = myAudioIndex;
}

public void myAudioDataWidget() {
	noLights();
	hint(DISABLE_DEPTH_TEST);
	noStroke(); fill(0,200); rect(0, height-112, width, 102);

	for (int i = 0; i < myAudioRange; ++i) {
		if 		(i==0) 	fill(0xff237D26); // base / sub item 0
		else if (i==3) 	fill(0xff80C41C); // snare / sub item 3
		else			fill(0xffCCCCCC); // others

		rect(10 + (i*5), (height-myAudioData[i])-11, 4, myAudioData[i]);
	}
	hint(ENABLE_DEPTH_TEST);
}

public void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}










  public void settings() { 	size(700, 700, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_02" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
