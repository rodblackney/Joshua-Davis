
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HCanvas c1;
HFollow mf;
HRect r1;
HPixelColorist colors;

void setup() {
	size(640, 640);
	H.init(this).background(#202020);
	smooth();

	c1 = new HCanvas().autoClear(false).fade(4);
	H.add(c1);

	colors = new HPixelColorist("sintra.jpg").fillAndStroke();
	// .fillOnly()
	//.strokeOnly()
	//.fillAndStroke()
	;

	r1 = new HRect(100).rounding(2);
	r1
		.strokeWeight(2)
		.stroke(#000000, 150)
		.fill(#111111)
		.loc(width/2, height/2)
		.anchorAt(H.CENTER)
		.rotation(45)
	;

	c1.add(r1);

	// - new HFollow(float ease)

	mf = new HFollow()
		.target(r1)
		.ease(0.10)
		.spring(0.20)
		;
}

void draw() {

	colors.applyColor(r1);

	H.drawStage();
	
}