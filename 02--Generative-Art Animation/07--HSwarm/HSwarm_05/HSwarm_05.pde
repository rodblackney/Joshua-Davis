
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
//import hype.extended.layout.*;
//import hype.interfaces.*;

HColorField colors;
HSwarm swarm;
HDrawablePool pool;
HTimer timer;

void setup() {
	size(640, 640);
	H.init(this).background(#000000).autoClear(false);
	smooth();

	colors = new HColorField(width, height)
		.addPoint(0, height/2, #FF0066, 0.5f)
		.addPoint(width, height/2, #3300FF, 0.5f)
		.fillOnly()
//		.strokeOnly()
//		.fillAndStroke()
	;

	swarm = new HSwarm()
		.addGoal(H.mouse())
		.speed(5)
		.turnEase(0.05f) // uses float to set degrees 
		.twitch(20)
	;

	pool = new HDrawablePool(40); // draw 40 assets
	pool.autoAddToStage() // add to stage
		.add (
			new HRect()
			.rounding(4)
			.size(18, 6)
		)

		.onCreate (
			new HCallback() {
				public void run(Object obj) {
					HDrawable d = (HDrawable) obj;
					d
						.size( (int)random(10,20), (int)random(2,6) )
						.stroke(#202020, 100)
						.fill(#000000)
						.loc( width/2, height/2) // assets start points
						.anchorAt(H.CENTER)
					;
						swarm.addTarget(d);
				}
			}
		)
	;
		
	timer = new HTimer()
		.numCycles( pool.numActive() )
		.interval(250)
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

	for(HDrawable d : pool) {
		colors.applyColor(d.fill(#000000));
	}


	H.drawStage();

}





