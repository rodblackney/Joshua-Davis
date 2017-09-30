HRect d;
HColorField colors;

void setup(){
	size(600,600);
	H.init(this).background(#202020);
	smooth();


	colors = new HColorField(width,height)
		.addPoint(width/2, height/2, #FF3300, 0.5) // orange
		.addPoint(width, height/2, #0095A8, 0.5) // teal

		.addPoint(width/2, 0, #FFFF00, 0.3) // yellow
		.addPoint(width/2, height/2, #00FF00, 0.3) // green
		.fillAndStroke()
	;

	for (int i = 0; i<200; i++){
		d = new HRect();
		d

			.strokeWeight(2)
			.stroke(#000000)
			.fill(#000000 , 100) // adding alpha
			.size( (int)random(25,125) )
			.rotate( (int)random(360) )
			.loc( (int)random(width), (int)random(height) )
			.anchorAt(H.CENTER)
		;

		colors.applyColor(d);
		H.add(d);
	}

	H.drawStage();
}
