
// HRotate

import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HRect r1;


void setup() {

	size(600, 500);
	H.init(this).background(#202020);
	smooth();

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc(width/2, height/2)
		.noStroke()
		.fill(#FF3300)
		;

		// shorthand HRotate
		new HRotate(r1, 2);
//		new HRotate(r1, -2); // counter clockwise
}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
	
}