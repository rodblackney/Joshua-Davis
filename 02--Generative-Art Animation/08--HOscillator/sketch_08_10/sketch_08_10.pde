import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HDrawablePool pool;

void setup() {
	size(600, 500);
	H.init(this).background(#202020);
	smooth();

	pool = new HDrawablePool(90);
	pool.autoAddToStage()
	.add(
		new HRect(6)
		.rounding(2)
		.anchorAt(H.CENTER)
		.noStroke()
	)

	.colorist( new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616f, #FF3300, #FF6600).fillOnly() )

	.layout(
		new HGridLayout()
		.startLoc(9, height/2)
		.spacing(7, 0)
		.cols(90)
		)

	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HDrawable d = (HDrawable) obj;

				new HOscillator()
					.target(d)
					.property(H.HEIGHT)
					.range(6, 200)
					.speed(1)
					.freq(3)
					// waveform saw
					.waveform(H.SAW)
					.currentStep( pool.currentIndex()*3 ) 
				;
			}
		}
	)

	.requestAll()
;
}

void draw() {

H.drawStage();

noFill(); strokeWeight(2); stroke(#CCCCCC);
// line(0, height/2, width, height/2);

	
}






