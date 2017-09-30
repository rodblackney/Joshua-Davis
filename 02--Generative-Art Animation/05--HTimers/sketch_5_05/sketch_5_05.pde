
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HDrawablePool pool;
HTimer timer;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	pool = new HDrawablePool(100);
	pool.autoAddToStage()
		.add (
			new HRect()
			.rounding(10)
			)

			.colorist(new HColorPool(#FFFFFF, #F7F7F7, #333333, #0095A8, #FF3300, #FF6600).fillOnly())

// what happens to each HRect from Pool
		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.strokeWeight(4)
						.stroke(#000000, 75)
						.loc( (int)random(width), (int)random(height) )
						.rotation(45)
						.size( 50+((int)random(3)*50) ) // 3 X 50 + 50
						.anchorAt( H.CENTER )
					;
				}
			}
		)
	;



	timer = new HTimer()
		.numCycles( pool.numActive() ) // how many cycles
		.interval(100) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					pool.request();
				}
			}
		)
	;
}

void draw() {
	H.drawStage();
}

