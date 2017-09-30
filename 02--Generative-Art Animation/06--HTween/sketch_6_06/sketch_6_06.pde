
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// behaviour stacking

HRect r1;
HTween t1, t2;
HCallback tweenDone1, tweenDone2;
HCallback rotateDone1, rotateDone2;

PVector pt1 = new PVector(160, 320); // left
PVector pt2 = new PVector(480, 320); // right

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	// rectangle settings
	r1 = new HRect(100).rounding(10);
	H.add(r1).anchorAt(H.CENTER).loc(pt1.x, pt1.y).noStroke().fill(#FF3300)
		;

	// left to right loop
	t1 = new HTween()
		.target(r1).property(H.LOCATION).start( pt1.x, pt1.y, pt1.z ).end( pt2.x, pt2.y, pt2.z ).ease(0.02).spring(0.7)
		;	

	tweenDone1 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt2.x, pt2.y)
				.end( pt1.x, pt1.y)
				.register().callback(tweenDone2)
				;
		}
	};

	tweenDone2 = new HCallback() {
		public void run(Object obj) {
			t1
				.start( pt1.x, pt1.y)
				.end( pt2.x, pt2.y)
				.register().callback(tweenDone1)
				;
		}
	};


// Rotate Loop 0 to 80

t2 = new HTween().target(r1).property(H.ROTATION).start( 0 ).end( 180 ).ease(0.02).spring(0.7);

rotateDone1 = new HCallback() {
	public void run(Object obj) {
		t2.start(180).end(0).register().callback(rotateDone2);
	}
};


rotateDone2 = new HCallback() {
	public void run(Object obj) {
		t2.start(0).end(180).register().callback(rotateDone1);
	}
};


	t1.register().callback(tweenDone1);
	t2.register().callback(rotateDone1);

}

void draw() {
	H.drawStage();

	noFill(); strokeWeight(2); stroke(#0095A8);
	ellipse(pt1.x, pt1.y, 4, 4); // point a ellipse
	ellipse(pt2.x, pt2.y, 4, 4); // point b ellipse
}



