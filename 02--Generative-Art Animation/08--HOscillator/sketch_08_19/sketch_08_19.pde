import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
// import hype.interfaces.*;

HDrawablePool pool;

void setup() {
	size(640,640,P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

	pool = new HDrawablePool(49);
	pool.autoAddToStage()
		.add (
			new HPath()

		)

	.colorist(new HColorPool( #FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600).fillOnly() )

	.layout (
		new HGridLayout()
		.startX(81)
		.startY(81)
		.spacing(80, 80)
		.cols(7)
		)

	.onCreate (
		new HCallback() {
			public void run(Object obj) {
				int ranEdges = round( random(5, 10) );
				float ranDepth = random (0.25, 0.75);

				HPath path = (HPath) obj;
				path
					.star( ranEdges, ranDepth )
					.size(64)
					.noStroke()
					.anchorAt(H.CENTER)
					.rotation( (int)random(360) )

					;

					new HOscillator()
						.target(path)
						.property(H.ROTATIONY)
						.range(-180, 180)
						.speed(0.5)
						.freq(5)
						.currentStep(pool.currentIndex())
					;

					new HOscillator()
						.target(path)
						.property(H.ROTATIONZ)
						.range(-180, 180)
						.speed(0.3)
						.freq(5)
						.currentStep(pool.currentIndex())
					;


					new HOscillator()
						.target(path)
						.property(H.Z)
						.range(-50, 50)
						.speed(2)
						.freq(1)
						.currentStep(pool.currentIndex()*8)
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






