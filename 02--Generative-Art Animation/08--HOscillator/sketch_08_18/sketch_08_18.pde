import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HCanvas c1;
HColorPool colors;
HRect d;


void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	c1 = new HCanvas().autoClear(false).fade(2);
	H.add(c1);

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600);

	// attach HRects
	for (int i=0; i<200; ++i) {

		d = new HRect().rounding(20);
		d
			.noStroke()
			.fill( colors.getColor(i * 250) )
			.loc(width/2, height/2)
			.anchor(50, -50)
		;
		c1.add(d);

		// Rotation
		new HOscillator()
			.target(d)
			.property(H.ROTATION)
			.range(-180, 180)
			.speed(0.1)
			.freq(4)
			.currentStep(i)
			;

		// Scale	
		new HOscillator()
			.target(d)
			.property(H.SCALE)
			.range(0.25, 1)
			.speed(1)
			.freq(4)
			.currentStep(i*3)
			;	

		// y position	
		new HOscillator()
			.target(d)
			.property(H.Y)
			// from original y location
			.relativeVal( d.y() )
			.range(-300, 300)
			.speed(0.5)
			.freq(2)
			.currentStep(i)
			;	

		// x position	
		new HOscillator()
			.target(d)
			.property(H.X)
			// from original x location
			.relativeVal( d.x() )
			.range(-300, 300)
			.speed(0.5)
			.freq(1)
			.currentStep(i)
			;	

		}
	}



void draw() {

H.drawStage();

	
}






