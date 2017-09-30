import hype.*;
import hype.extended.behavior.*;

HEllipse d;

void setup() {
	size(600, 600);
	H.init(this).background(#202020);
	smooth();

// draw 100 HRect to screen
for (int i = 0; i < 100; ++i) {
	// add rounding
	d = new HEllipse();
	d

		.strokeWeight(1)
		.stroke(#FF3300)
		// changes alpha to 200
		.fill(#111111, 200)
		// random size number with a range between 25 & 125
		.size( (int)random(25,125), (int)random(50,150) )
		// rotates randomly
		.rotate( (int)random(360) )
		// chooses a random location		
		.loc( (int)random(width), (int)random(height) )
		// centers at middle of HRect	
		.anchorAt(H.CENTER);

		H.add(d);

	}

	H.drawStage();

}

