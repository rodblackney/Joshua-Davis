
// HRotate

import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HRect r1, r2, r3;


void setup() {

	size(600, 500);
	H.init(this).background(#202020);
	smooth();

	// rectangle #1
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchorAt(H.CENTER) // adds anchor point constant
		.loc(width/2 -160, height/2)
		.noStroke()
		.fill(#FF3300)
		;

	// rectangle #2
	r2 = new HRect(100).rounding(10);
	H.add(r2)
		.anchorAt(H.CENTER)
		.loc(width/2, height/2)
		.noStroke()
		.fill(#FF6600)
		;

	// rectangle #3
	r3 = new HRect(100).rounding(10);
	H.add(r3)
		.anchorAt(H.CENTER)
		.loc(width/2 + 160, height/2)
		.noStroke()
		.fill(#FF9900)
		;			

		new HRotate(r1, 0.5);
		new HRotate(r2, 1);
		new HRotate(r3, 1.5);
}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
	ellipse(r2.x(), r2.y(), 4, 4);
	ellipse(r3.x(), r3.y(), 4, 4);
	
}