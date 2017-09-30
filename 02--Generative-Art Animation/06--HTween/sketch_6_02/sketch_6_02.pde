
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HRect r1;
HTween t1;

int startX = 160;
int endX = 480;

void setup() {
	size(600, 500);
	H.init(this).background(#202020);
	smooth();

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchorAt(H.CENTER)
		.loc(startX, height/2)
		.noStroke()
		.fill(#FF3300)
		;

	// tween from left to right	
	t1 = new HTween()
		.target(r1)
		.property(H.LOCATION)
		.start(startX, height/2)
		.end(endX, height/2)
		.ease(0.01)
		.spring(0.95)
	;	
}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(startX, height/2, 4, 4);
	ellipse(endX, height/2, 4, 4);
}



