import hype.*;
// import hype.extended.behavior.*;
import hype.extended.colorist.*;
// import hype.extended.layout.*;
// import hype.interfaces.*;

HRect d;
HColorPool colors;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();


	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095A8, #00616F, #FF3300, #FF6600);

	for (int i = 0; i<100; i++){
		d = new HRect();
		d
			.strokeWeight(1)
			.stroke(#000000)
			.fill( colors.getColor()  )
			.size( (int)random(25,75) )
			.rotate( (int)random(360) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;
		H.add(d);
	}

	H.drawStage();
}
