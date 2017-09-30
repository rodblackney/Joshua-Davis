import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;


HColorPool colors;
HRect d;

// int startScale = 450;
// int scaleOffset = 10;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600);

	// attach 200 HRect
	for (int i=0; i<200; ++i) {

		d = new HRect().rounding(20);
		d
	//		.size(startScale)
			.noStroke()
			.fill( colors.getColor() )
			.loc(width/2, height/2)
			.anchor(50, -50)
		;
		H.add(d);

		new HOscillator()
			.target(d)
			.property(H.ROTATION)
			.range(-180, 180)
			.speed(0.1)
			.freq(4)
			.currentStep(i)
			;


		new HOscillator()
			.target(d)
			.property(H.SCALE)
			.range(0.25, 1)
			.speed(1)
			.freq(4)
			.currentStep(i*3)
			;	

		new HOscillator()
			.target(d)
			.property(H.Y)
			// from original y location
			.relativeVal( d.y() )
			.range(-150, 150)
			.speed(0.5)
			.freq(2)
			.currentStep(i)
			;	

		new HOscillator()
			.target(d)
			.property(H.X)
			// from original x location
			.relativeVal( d.x() )
			.range(-50, 50)
			.speed(0.5)
			.freq(1)
			.currentStep(i)
			;	

			
		// uses scaleOffset to -10 from startScale
		// 440, 430, 420, 410
//		startScale -= scaleOffset;

		}
	}



void draw() {

H.drawStage();

	
}






