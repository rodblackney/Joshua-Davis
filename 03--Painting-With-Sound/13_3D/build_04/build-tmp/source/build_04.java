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

public class build_04 extends PApplet {










Minim 		minim;
AudioPlayer	myAudio;
FFT			myAudioFFT;

boolean 	showVisualizer 		= false;

int 		myAudioRange		= 11;
int 		myAudioMax 			= 100;

float 		myAudioAmp			= 40.0f;
float 		myAudioIndex		= 0.2f;
float 		myAudioIndexAmp		= myAudioIndex;
float 		myAudioIndexStep	= 0.35f;

float[] myAudioData				= new float [myAudioRange];

HDrawablePool pool;

					// base						// snare
int [] palette = {0xff000000,0xff666666,0xff666666,	0xffFFFFFF, 0xff666666,0xff666666,0xff666666,0xff666666,0xff666666,0xff666666,0xff666666};

int poolCols	= 5;
int poolRows	= 5;
int poolDepth	= 5;

int rotateNumX	= 0;
int rotateNumY	= 0;
int rotateNumZ	= 0;


public void setup() {
	
	H.init(this).background(0xffFF3300).use3D(true).autoClear(true);

	minim   = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	// myAudioFFT.window(FFT.GAUSS);

	pool = new HDrawablePool(poolCols*poolRows*poolDepth);
	pool.autoAddToStage()
		.add ( new HSphere() )
		.layout (new HGridLayout().startX(-300).startY(-300).startZ(-300).spacing(150, 150, 150).rows(poolRows).cols(poolCols))
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					int ranIndex = (int)random(myAudioRange);

					HSphere d = (HSphere) obj;
					d
						.size(10)
						.strokeWeight(0)
						.noStroke()
						.fill(palette[ranIndex], 255)
						.anchorAt(H.CENTER)
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
	sphereDetail(20);

	pushMatrix();
		
		translate(width/2, height/2, -900);
			
		rotateX( map(rotateNumX, 0, myAudioMax, -TWO_PI/20, TWO_PI/20) );
		rotateY( map(rotateNumY, 0, myAudioMax, -TWO_PI/20, TWO_PI/20) );
		rotateZ( map(rotateNumZ, 0, myAudioMax, -TWO_PI/20, TWO_PI/20) );

		int fftRotateX = (int)map(myAudioData[0], 0, myAudioMax, -1, 20);
		int fftRotateY = (int)map(myAudioData[3], 0, myAudioMax, -1, 20);
		int fftRotateZ = (int)map(myAudioData[5], 0, myAudioMax, 1, -20);

		rotateNumX += fftRotateX;
		rotateNumY += fftRotateY;
		rotateNumZ += fftRotateZ;

		H.drawStage();

		// stroke (#333333); fill(#242424, 50); box(600); noStroke(); noFill(); 

	popMatrix();

	for (HDrawable d : pool) {
		HBundle tempExtra = d.extras();
		int i = (int)tempExtra.num("i");

		int fftSize;

		if (i==0) 		fftSize = (int)map(myAudioData[i], 0, myAudioMax, -350, 350);
		else if (i==3)	fftSize = (int)map(myAudioData[i], 0, myAudioMax, 50, 350);
		else			fftSize = (int)map(myAudioData[i], 0, myAudioMax, 2, 150);

		d.size(fftSize);
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
    String[] appletArgs = new String[] { "build_04" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
