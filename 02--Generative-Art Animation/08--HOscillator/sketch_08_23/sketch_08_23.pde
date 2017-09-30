import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HColorPool colors;
HDrawablePool pool;

float ringScale = 200;
int ringSteps = 5;

void setup() {
	size(640,640,P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #FF3300, #242424, #333333, #666666);

	pool = new HDrawablePool(150);
	pool.autoAddToStage()
		.add(
			new HShape("ring.svg")
		)

	.layout (
		new HGridLayout()
		.startX(width/2)
		.startY(height/2)
		.spacing(0, 0)
		.cols(10)
		)


	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				int i = pool.currentIndex();
					HShape d = (HShape) obj;
					d
						.enableStyle(false)
						.noStroke()
						.fill( colors.getColor(i*250) )
						.size( ringScale )
						.anchorAt(H.CENTER)
						.z(0)
				;

				ringScale = ringScale - ringSteps;

				// x axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONX)
					.range(-360, 360)
					.speed(0.1)
					.freq(1)
					.currentStep(i)	
				;

				// y axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONY)
					.range(-180, 180)
					.speed(0.3)
					.freq(1)
					.currentStep(i*2)
				;

				// z axis
				new HOscillator()
					.target(d)
					.property(H.ROTATIONZ)
					.range(-360, 360)
					.speed(0.5)
					.freq(1)
					.currentStep(i*2)
				;




				// scale
				new HOscillator()
					.target(d)
					.property(H.SCALE)
					.range(0.5, 1.0)
					.speed(0.3)
					.freq(5)
					.currentStep(i)
				;

			}
		}
	)

		.requestAll()
	;
}



void draw() {


H.drawStage();

	
}






