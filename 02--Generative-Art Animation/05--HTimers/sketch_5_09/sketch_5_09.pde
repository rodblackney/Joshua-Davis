
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times


HCanvas c1;
HColorPool colors;
HDrawablePool pool;
HRandomTrigger randomTimer;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	randomTimer = new HRandomTrigger( 1f/30 );

	c1 = new HCanvas().autoClear(false).fade(5);
	H.add(c1);

	colors = new HColorPool(#FFFFFF, #F7F7F7, #333333, #0095A8, #FF3300, #FF6600);

	pool = new HDrawablePool(50);
	pool.autoParent(c1)
		.add (
			new HRect()
			.rounding(40)
			)

// what happens to each HRect from Pool
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.noStroke()
						.fill(colors.getColor(), 200)
						.loc( (int)random(width), (int)random(height) )
						.size( 25 + ((int)random(4) * 25) )
						.anchor(0, -50 )
					;

					new HRotate(d, random(-4, 4)) ;
					// parse HOscillator argumments
					new HOscillator().target(d).property(H.SCALE).range(-1, 1).speed(0.5).freq(5).currentStep(pool.currentIndex());
				}
			}
		)
	;



	randomTimer.callback(
		new HCallback() {
			public void run(Object obj) {
				pool.request();
			}
		}
	);
}

void draw() {
	H.drawStage();
}

