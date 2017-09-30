
import hype.*;
import hype.extended.behavior.*;
// import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

// display hello world 10 times

HTimer timer;
int counter = 1;

void setup() {
	size(640,640);
	H.init(this).background(#202020);
	smooth();

	timer = new HTimer()
		.numCycles(10) // timer length
		.interval(500) // between each time in milliseconds
		.callback( // what happens
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

