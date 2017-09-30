import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;


HTimer timer;
int counter = 1;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	timer = new HTimer()
		.numCycles( 10 )
		.interval(500)
		.callback(
			new HCallback() { 
				public void run(Object obj) {
					println("hello, world - " + counter);
					++counter;
				}
			}
		)
	;
}

void draw() {
	H.drawStage();
}

