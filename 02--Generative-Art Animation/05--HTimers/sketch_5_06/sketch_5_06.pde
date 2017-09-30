
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times


HCanvas c1;
HColorPool colors;
HDrawablePool pool;
HTimer timer;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	c1 = new HCanvas().autoClear(false).fade(5);
	H.add(c1);

	colors = new HColorPool(#FFFFFF, #F7F7F7, #333333, #0095A8, #FF3300, #FF6600);

	pool = new HDrawablePool(100);
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

					new HRotate(d, random(-6, 6)) ;
				}
			}
		)
	;



	timer = new HTimer()
		.numCycles( pool.numActive() ) // how many cycles
		.interval(120) // between each time in milliseconds
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

