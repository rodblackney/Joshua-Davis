import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;


HColorPool colors;
HRect r1;

int startScale = 450;
int scaleOffset = 10;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600);

	for (int i=0; i<40; ++i) {

		r1 = new HRect().rounding(20);
		r1
			.size(startScale)
			.noStroke()
			.fill( colors.getColor() )
			.loc(width/2, height/2)
			.anchorAt(H.CENTER)
			.rotation(45)
		;
		H.add(r1);

		new HOscillator()
			.target(r1)
			.property(H.ROTATION)
			.relativeVal(45)
			.range(-45, 45)
			.speed(0.4)
			.freq(10)
			.currentStep(i)
			;


		new HOscillator()
			.target(r1)
			.property(H.SCALE)
			.range(0.5, 1.5)
			.speed(0.2)
			.freq(8)
			.currentStep(i * 2)
			;	

		new HOscillator()
			.target(r1)
			.property(H.Y)
			// from original y location
			.relativeVal( r1.y() )
			.range(-75, 75)
			.speed(0.5)
			.freq(5)
			.currentStep(i)
			;	

		new HOscillator()
			.target(r1)
			.property(H.X)
			// from original x location
			.relativeVal( r1.x() )
			.range(-50, 50)
			.speed(0.5)
			.freq(8)
			.currentStep(i * 2)
			;	

			
		// uses scaleOffset to -10 from startScale
		// 440, 430, 420, 410
		startScale -= scaleOffset;

		}
	}



void draw() {

H.drawStage();

	
}






