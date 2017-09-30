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

public class build_21 extends PApplet {










Minim 		minim;
AudioPlayer	myAudio;
FFT			myAudioFFT;

boolean 	showVisualizer 	= false;

int 		myAudioRange		= 11;
int 		myAudioMax 			= 100;

float 		myAudioAmp		= 40.0f;
float 		myAudioIndex		= 0.2f;
float 		myAudioIndexAmp	= myAudioIndex;
float 		myAudioIndexSetup	= 0.35f;

float[] myAudioData		= new float [myAudioRange];

HDrawablePool pool;
int poolMax = 100;

public void setup() {
	
	H.init(this).background(0xff202020);

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
		.add ( new HRect(100).rounding(10) )
		.onCreate (
			new HCallback() {
				public void run(Object obj) {

					int ranIndex = (int)random(myAudioRange);
					HDrawable d = (HDrawable) obj;
					d
						.stroke(0)
						.fill(255, 255)
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

public void draw() {
	myAudioFFT.forward(myAudio.mix);
	myAudioDataUpdate();

	H.drawStage();

	for (HDrawable d : pool) {
		HBundle tempExtra = d.extras();
		int i = (int)tempExtra.num("i");

		int fftFillColor = (int)map(myAudioData[i], 0, myAudioMax, 0, 255);
		d.fill(fftFillColor, 225);

		// visualize under diamonds which random numbers got picked

		if (i==0) {
			fill(0xff237D26); // base  / subitem 0
		} else if (i==3) {
			fill(0xff80C41C); // snare / subitem 3
			d.y(height/2-150);
		} else {
			fill(0xffFF3300); // all others
		}

		textSize(12); textAlign(CENTER); text(i, d.x(), d.y() );
	}

	if (showVisualizer) myAudioDataWidget();
}


public void myAudioDataUpdate() {

	for (int i = 0; i < myAudioRange; ++i) {
		float tempIndexAvg 	= (myAudioFFT.getAvg(i) * myAudioAmp) * myAudioIndexAmp;
		float tempIndexCon = constrain(tempIndexAvg, 0, myAudioMax);
		myAudioData[i]		= tempIndexCon;
		myAudioIndexAmp += myAudioIndexSetup;
	}
	myAudioIndexAmp = myAudioIndex;
}

public void myAudioDataWidget() {
	// noLights();
	// hint(DISABLE_DEPTH_TEST);
	noStroke(); fill(0,200); rect(0, height-112, width, 102);

	for (int i = 0; i < myAudioRange; ++i) {
		if 		(i==0) 	fill(0xff237D26); // base / sub item 0
		else if (i==3) 	fill(0xff80C41C); // snare / sub item 3
		else			fill(0xffCCCCCC); // others

		rect(10 + (i*5), (height-myAudioData[i])-11, 4, myAudioData[i]);
	}
	// hint(ENABLE_DEPTH_TEST);
}

public void stop() {
	myAudio.close();
	minim.stop();
	super.stop();
}










  public void settings() { 	size(700, 700); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_21" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
