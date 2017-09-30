
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;


HRandomTrigger randomTrigger;
HRect r1;


void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	/*
	* Create a new randomTrigger with a 1 in 15 chance
	* of triggering everytime H.drawStage() is called
	*/

randomTrigger = new HRandomTrigger(1f/15);
// 1f/15 chance 1 in 15 chance

r1 = new HRect(100).rounding(10);
H.add(r1)
	.loc( (int)random(width),(int)random(height))
	.noStroke()
	.fill(#FF3300)
	.rotation(45)
	;

randomTrigger.callback(
	new HCallback() {
		public void run(Object obj) {
			r1.loc ( (int)random(width), (int)random(height) );
			}
		}

		)
	;
}




void draw() {
	H.drawStage();
}

