import hype.*;
import hype.extended.behavior.*;

// Box

HBox d;

void setup() {
	size(600, 600, P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

// draw 100 HRect to screen
for (int i = 0; i < 20; ++i) {
	d = new HBox();
	d


		// box takes depth, width and height
		.depth( (int)random(25,125) )
		.width( (int)random(25,125) )
		.height( (int)random(25,125) )


		.strokeWeight(1)
		.stroke(#FF3300)
		.fill(#111111, 200)
		// random size number with a range between 25 & 125
		.size( (int)random(50,150), (int)random(75,125) )
		// chooses a random location		
		.loc( (int)random(width), (int)random(height) )
		// centers at middle of HRect	
		.anchorAt(H.CENTER);

		H.add(d);

	}

	H.drawStage();

}

