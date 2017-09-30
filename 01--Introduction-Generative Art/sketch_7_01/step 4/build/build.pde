import hype.*;
import hype.extended.behavior.*;
import hype.extended.colorist.*;
import hype.extended.layout.*;
import hype.interfaces.*;


HDrawablePool pool;

void setup() {
	size(600, 600);
	H.init(this).background(#202020);
	smooth();

	// draw 100 assets
	pool = new HDrawablePool(100);
	pool.autoAddToStage()
	// what
	.add( new HRect(),10 )
	.add( new HRect().rounding(10) )	
	// 
	.onCreate(
		new HCallback() {
			public void run(Object obj) {
				HDrawable d = (HDrawable) obj;
				d
					.strokeWeight(1)
					.stroke(#FF3300)
					.fill(#111111)
					.size( (int)random(25,125) )
					.rotate( (int)random(360) )
					.loc( (int)random(width), (int)random(height) )
					.anchorAt(H.CENTER)
					;
				}			
		}
	)
	.requestAll()
	;

	H.drawStage();
}
