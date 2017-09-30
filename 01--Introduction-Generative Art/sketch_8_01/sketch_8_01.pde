import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;


HDrawablePool pool;
HColorPool colors;

void setup() {
	size(600, 600);
	H.init(this).background(#202020);
	smooth();

	colors = new HColorPool(#FFFFFF, #F7F7F7, #ECECEC, #333333, #0095a8, #00616f, #FF3300, #FF6600);

	// draw 100 assets
	pool = new HDrawablePool(2000);
	pool.autoAddToStage()
	// one in six chance of being drawn
//	.add( new HShape("svg1.svg"))
//	.add( new HShape("svg2.svg"))
//	.add( new HShape("svg3.svg"))
//	.add( new HShape("svg4.svg"))
	.add( new HShape("svg5.svg"))
//	.add( new HShape("svg6.svg"))

	.layout (
		new HShapeLayout()
		.target(
			new HImage("shapeMap.png")
		)
	)						

	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HShape d = (HShape) obj;
				d

					.enableStyle(false)
					.noStroke()
//					.strokeJoin(ROUND)
//					.strokeCap(ROUND)
//					.strokeWeight(1)
//					.stroke(#000000)
					.anchorAt(H.CENTER)
					// shrink svg size
					.size( (int)random(10,30) )
					.rotate( (int)random(360) )
					;

					d.randomColors(colors.fillOnly());
				}			
		}
	)
	.requestAll()
	;

	H.drawStage();
}
