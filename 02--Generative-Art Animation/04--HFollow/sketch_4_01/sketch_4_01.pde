
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HFollow mf;
HRect r1;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	r1 = new HRect(100).rounding(40);
	r1
		.noStroke()
		.fill(#ECECEC)
		.loc(width/2, height/2)
		.anchorAt(H.CENTER)
		.rotation(45)
	;

	H.add(r1);

	// - new HFollow()
	// - new HFollow(float ease)
	// - new HFollow(float ease, float spring)
	// = new HFollow(float ease, float spring, HFollowable goal)

	mf = new HFollow().target(r1);



}

void draw() {
	H.drawStage();
	
}