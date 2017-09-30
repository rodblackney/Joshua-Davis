
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
//import hype.extended.layout.*;
//import hype.interfaces.*;

HSwarm swarm;
HDrawablePool pool;
HTimer timer;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	swarm = new HSwarm()
		.addGoal(width/2, height/2)
		.speed(4)
		.turnEase(0.025f) // uses float to set degrees 
		.twitch(0)
	;

	pool = new HDrawablePool(40); // draw 40 assets
	pool.autoAddToStage() // add to stage
		.add (
			new HRect()
			.rounding(4)
			.size(18, 6)
			)

		.colorist(new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095a8, #00616f, #FF3300, #FF6600).fillOnly())

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					float rotation = random(TWO_PI);
					HDrawable d = (HDrawable) obj;
					d
						.noStroke()
						.loc( width/2, 100) // assets start points
						.rotationRad(rotation)
						.move( cos(rotation)*10, sin(rotation)*10 )
						.anchorAt(H.CENTER)
						;
						swarm.addTarget(d);
				}
			}
		)
	;
		
	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(180)
		.callback(
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

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(width/2, 100, 4, 4); // start point
	stroke(#FF3300);
	ellipse(width/2, height/2, 4, 4); // end point
}





