
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HTimer timer;
HRect r1;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc( (int)random(width), (int)random(height) )
		.noStroke()
		.fill(#FF3300)
		.rotation(45)
	;



	timer = new HTimer()
		.numCycles(20) // how many
		.interval(250) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					r1.loc( (int)random(width), (int)random(height));
				}
			}
		)
	;
}

void draw() {
	H.drawStage();
}

