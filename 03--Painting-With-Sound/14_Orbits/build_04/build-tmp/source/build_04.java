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










Minim 			minim;
AudioPlayer		myAudio;
FFT				myAudioFFT;

boolean 		showVisualizer 		= true;

int 			myAudioRange		= 11;
int 			myAudioMax 			= 100;

float 			myAudioAmp			= 40.0f;
float 			myAudioIndex		= 0.2f;
float 			myAudioIndexAmp		= myAudioIndex;
float 			myAudioIndexStep	= 0.35f;

float[] myAudioData				= new float [myAudioRange];


HDrawablePool pool;
int poolMax 		= 6;

					// red 		green
int[] palette 	= { 0xffFF0000,	0xff00FF00, 0xff0000FF, 0xff00FFFF, 0xffFFFF00, 0xffFF00FF};

int 			oRadiusMin 		= 125;
int 			oRadiusMax 		= 250;

float  			oSpeedZmin       = 0.05f;
float         	oSpeedZmax       = 5.0f;

float         	oSpeedYmin       = -0.05f;
float         	oSpeedYmax       = -5.0f;

int           rotateNumX       = 0;
int           rotateNumY       = 0;
int           rotateNumZ       = 0;

public void setup() {
	
	H.init(this).background(0xff202020).use3D(true).autoClear(true);

	minim   = new Minim(this);
	myAudio = minim.loadFile("HECQ_With_Angels_Trifonic_Remix.wav");
	myAudio.loop();

	myAudioFFT = new FFT(myAudio.bufferSize(), myAudio.sampleRate());
	myAudioFFT.linAverages(myAudioRange);
	// myAudioFFT.window(FFT.GAUSS);

	pool = new HDrawablePool(poolMax);
	pool.autoAddToStage()
		.add ( new HSphere() )
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					int i = pool.currentIndex();

					HDrawable d = (HDrawable) obj;
					d
						.size(10)
						.noStroke()
						.fill(palette[i])
						.anchorAt(H.CENTER)
					;

					HOrbiter3D orb = new HOrbiter3D(0, 0, 0)
						.target(d)
						.zSpeed(oSpeedZmin)
						.ySpeed(oSpeedYmin)
						.radius(oRadiusMax)
						.zAngle( (int)random(360) )
						.yAngle( (int)random(360) )
					;

					d.extras( new HBundle().num("i", i).obj("o", orb) ) ;

				}
			}
		)
		.requestAll()
	;
}


public void draw() {
	myAudioFFT.forward(myAudio.mix);
	myAudioDataUpdate();

	noLights();

	pushMatrix();
		translate(width/2, height/2, 0);

		rotateX( map(rotateNumX, 0, myAudioMax, -(TWO_PI / 20), TWO_PI / 20) );
		rotateY( map(rotateNumY, 0, myAudioMax, -(TWO_PI / 20), TWO_PI / 20) );
		rotateZ( map(rotateNumZ, 0, myAudioMax, -(TWO_PI / 20), TWO_PI / 20) );

		int fftRotateX = (int)map(myAudioData[0], 0, myAudioMax, -1,  20); // controlled by [0] base
		int fftRotateY = (int)map(myAudioData[3], 0, myAudioMax, -1,  20); // controlled by [3] snare
		int fftRotateZ = (int)map(myAudioData[5], 0, myAudioMax,  1, -20); // controlled by [5] just arbitrary

		rotateNumX += fftRotateX;
		rotateNumY += fftRotateY;
		rotateNumZ += fftRotateZ;

		lights();
		sphereDetail(10);
		H.drawStage();
		noLights();

		for (HDrawable d : pool) {
			HBundle tempExtra = d.extras();
			int i = (int)tempExtra.num("i");
			HOrbiter3D o = (HOrbiter3D) tempExtra.obj("o");

			// change speed of orbits based on audio reaction

			int   fftRadius;
			float fftZspeed, fftYspeed;

			switch (i) {
				case 0 :
					pointLight(255, 0, 0,   o.x(), o.y(), o.z()); // RED
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;

				case 1 :
					pointLight(0, 255, 0,   o.x(), o.y(), o.z()); // GREEN
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;

				case 2 :
					pointLight(0, 0, 255,   o.x(), o.y(), o.z()); // BLUE
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;

				case 3 :
					pointLight(0, 255, 255, o.x(), o.y(), o.z()); // CYAN
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;

				case 4 :
					pointLight(255, 255, 0, o.x(), o.y(), o.z()); // YELLOW
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;

				case 5 :
					pointLight(255, 0, 255, o.x(), o.y(), o.z()); // MAGENTA
					fftRadius = (int)map(myAudioData[i], 0, myAudioMax, oRadiusMin, oRadiusMax);
					fftZspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedZmin, oSpeedZmax);
					fftYspeed =      map(myAudioData[i], 0, myAudioMax, oSpeedYmin, oSpeedYmax);
					o.zSpeed(fftZspeed).ySpeed(fftYspeed).radius(fftRadius);
				break;
			}
		}

		// draw sphere in the center

		pushMatrix();
			translate(0, 0, 0);
			sphereDetail(25);
			noStroke(); stroke(255); fill(255); sphere(125); noStroke(); noFill();
		popMatrix();

	popMatrix();

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










  public void settings() { 	size(700, 700, OPENGL); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "build_04" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
