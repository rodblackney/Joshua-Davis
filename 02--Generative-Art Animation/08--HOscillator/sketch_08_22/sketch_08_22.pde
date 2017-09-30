import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
// import hype.interfaces.*;

HDrawablePool pool;
int boxSize = 500;

void setup() {
	size(640,640,P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add(
			new HBox()
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
					HBox d = (HBox) obj;
					d
						// .depth(boxSize * 3)
						.depth(boxSize)
						.width(boxSize)
						.height(boxSize)
						.noStroke()
						.z(-500)
				;

				// x axis	
				new HOscillator()
					.target(d)
					.property(H.ROTATIONX)
					.range(-180, 180)
					.speed(0.3)
					.freq(5)
					.currentStep(i*2)	
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
					.range(0.2, 1.2)
					.speed(0.3)
					.freq(5)
					.currentStep(i*2)
				;

			}
		}
	)

		.requestAll()
	;
}



void draw() {
	pointLight(255, 51, 0, 0, height/2, -300);
	pointLight(0, 149, 168, width, height/2, -100);
	pointLight(255, 284, 0, width/2, 0, -50);


H.drawStage();

	
}






