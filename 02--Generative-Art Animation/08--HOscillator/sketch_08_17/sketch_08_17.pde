import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HCanvas c1;
HColorPool colors;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	c1 = new HCanvas().autoClear(false);
	H.add(c1);

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600);

	// attach HPath
	for (int i=0; i<100; ++i) {

		HPath d = new HPath();
		d
			.star(20, 0.4, -90)
			.size(130)
			// get colors for stroke using random seed
			.stroke( colors.getColor( i*250) )
			.fill(#000000)
			.anchorAt(H.CENTER)
			.loc(width/2, height/2)
		;

		c1.add(d);

		// Rotation
		new HOscillator()
			.target(d)
			.property(H.ROTATION)
			.range(-180, 180)
			.speed(1)
			.freq(0.5)
			.currentStep(i)
			;

		// Scale	
		new HOscillator()
			.target(d)
			.property(H.SCALE)
			.range(0.25, 1)
			.speed(2)
			.freq(4)
			.currentStep(i)
			;	

		// Y position	
		new HOscillator()
			.target(d)
			.property(H.Y)
			// from original y location
			.relativeVal( d.y() )
			.range(-500, 500)
			.speed(1)
			.freq(0.7)
			.currentStep(i)
			;	

		// X position	
		new HOscillator()
			.target(d)
			.property(H.X)
			// from original x location
			.relativeVal( d.x() )
			.range(-500, 500)
			.speed(2)
			.freq(0.5)
			.currentStep(i)
			;	

		}
	}



void draw() {

H.drawStage();

	
}






