import hype.*;
// import hype.extended.behavior.*;

// Sphere

HSphere d;

void setup() {
	size(600, 600, P3D);
	H.init(this).background(#202020).use3D(true);
	smooth();

// draw 100 HRect to screen
for (int i = 0; i < 100; ++i) {
	d = new HSphere();
	d
		.size( (int)random(40,100) )
		.strokeWeight(1)
		.stroke(#FF3300)
		.fill(#111111)

		// add random axis rotation
		.rotationX( (int)random(360) )
		.rotationY( (int)random(360) )
		.rotationZ( (int)random(360) )		

		.loc( (int)random(width), (int)random(height), (int)random(-100,100) )
		// centers at middle of HRect	
		.anchorAt(H.CENTER);
		H.add(d);
	}
	H.drawStage();
}

