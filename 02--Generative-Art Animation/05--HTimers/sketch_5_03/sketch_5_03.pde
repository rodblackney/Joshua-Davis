
import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HTimer timer;
HRect r1;
HColorPool colors;

void setup() {
	size(640,640);
	H.init(this).background(#202020).autoClear(false);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #333333, #0095A8, #FF3300, #FF6600);

	r1 = new HRect(100).rounding(10);
	H.add(r1)
		.loc( (int)random(width), (int)random(height) )
		.noStroke()
		.fill(#FF3300)
		.rotation(45)
	;

	timer = new HTimer()
//		.numCycles(20) // how many cycles
		.interval(250) // between each time in milliseconds
		.callback( // what happens
			new HCallback() { 
				public void run(Object obj) {
					r1
						.loc( (int)random(width), (int)random(height))
						.fill( colors.getColor() )
						.size( 25 + ((int)random(3)*25) ) // picks between 0, 25, 50 then adds 25, 50, 75
					;
				}
			}
		)
	;
}

void draw() {
	H.drawStage();
}

