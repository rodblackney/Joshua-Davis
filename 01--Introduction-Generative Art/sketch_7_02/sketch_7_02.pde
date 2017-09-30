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
	pool = new HDrawablePool(60);
	pool.autoAddToStage()
	// what
	.add( new HShape("svg1.svg"))
	.add( new HShape("svg2.svg"))
	.add( new HShape("svg3.svg"))
	.add( new HShape("svg4.svg"))
	.add( new HShape("svg5.svg"))
	.add( new HShape("svg6.svg"), 20)						

	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HShape d = (HShape) obj;
				d
					.enableStyle(false)
					.strokeJoin(ROUND)
					.strokeCap(ROUND)
					.strokeWeight(1)
					.stroke(#000000)
					.size( (int)random(25,125) )
					.rotate( (int)random(360) )
					.loc( (int)random(width), (int)random(height) )
					.anchorAt(H.CENTER)
					;
					d.randomColors(colors.fillOnly());
				}			
		}
	)
	.requestAll()
	;

	H.drawStage();
}
