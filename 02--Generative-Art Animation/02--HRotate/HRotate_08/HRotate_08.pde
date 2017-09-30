
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;

HRect r1, r2;

void setup() {

	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchor(50, -50)
		.loc(width/2, height/2)
		.noStroke()
		.fill(#FF3300)
	;

	r2 = new HRect(50).rounding(10);
	r1.add(r2) // attach r2 to r1
		.anchor(25, -25)
		.noStroke()
		.fill(#00616F)
	;

	// nests smaller rect (r2) inside larger (r1)
	new HRotate(r1, 1);

}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(r1.x(), r1.y(), 4, 4);
}




