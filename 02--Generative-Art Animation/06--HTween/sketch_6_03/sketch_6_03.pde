
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HRect r1;
HTween t1;
HCallback tweenDone1, tweenDone2;

PVector pt1 = new PVector(160, 320); // left pos
PVector pt2 = new PVector(480, 320); // right pos

void setup() {
	size(640, 500);
	H.init(this).background(#202020);
	smooth();

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.anchorAt(H.CENTER)
		.loc(pt1.x, pt1.y)
		.noStroke()
		.fill(#FF3300)
		;

	// start tween from left to right	
	t1 = new HTween()
		.target(r1)
		.property(H.LOCATION)
		.start( pt1.x, pt1.y )
		.end( pt2.x, pt2.y )
		.ease(0.05)
		.spring(0.7)
	;	

	// arrives at right position, move back to left position

	tweenDone1 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt2.x, pt2.y )
				.end( pt1.x, pt1.y )
				.register().callback(tweenDone2)
				;
		}
	};


	// arrives at left position, move back to right position

	tweenDone2 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt1.x, pt1.y )
				.end( pt2.x, pt2.y )
				.register().callback(tweenDone1)
				;
		}
	};

	t1.register().callback(tweenDone1);


}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(pt1.x, pt1.y, 4, 4); // point a ellipse
	ellipse(pt2.x, pt2.y, 4, 4); // point b ellipse
}



